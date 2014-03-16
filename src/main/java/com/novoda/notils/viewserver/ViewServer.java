package com.novoda.notils.viewserver;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;

import java.io.*;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * <p>This class can be used to enable the use of HierarchyViewer inside an
 * application. HierarchyViewer is an Android SDK tool that can be used
 * to inspect and debug the user interface of running applications. For
 * security reasons, HierarchyViewer does not work on production builds
 * (for instance phones bought in store.) By using this class, you can
 * make HierarchyViewer work on any device. You must be very careful
 * however to only enable HierarchyViewer when debugging your
 * application.</p>
 * <p/>
 * <p>To use this view server, your application must require the INTERNET
 * permission.</p>
 * <p/>
 * <p>The recommended way to use this API is to register activities when
 * they are created, and to unregister them when they get destroyed:</p>
 * <p/>
 * <pre>
 * public class MyActivity extends Activity {
 *     public void onCreate(Bundle savedInstanceState) {
 *         super.onCreate(savedInstanceState);
 *         // Set content view, etc.
 *         ViewServer.get(this).addWindow(this);
 *     }
 *
 *     public void onDestroy() {
 *         super.onDestroy();
 *         ViewServer.get(this).removeWindow(this);
 *     }
 *
 *     public void onResume() {
 *         super.onResume();
 *         ViewServer.get(this).setFocusedWindow(this);
 *     }
 * }
 * </pre>
 * <p/>
 * <p>
 * In a similar fashion, you can use this API with an InputMethodService:
 * </p>
 * <p/>
 * <pre>
 * public class MyInputMethodService extends InputMethodService {
 *     public void onCreate() {
 *         super.onCreate();
 *         View decorView = getWindow().getWindow().getDecorView();
 *         String name = "MyInputMethodService";
 *         ViewServer.get(this).addWindow(decorView, name);
 *     }
 *
 *     public void onDestroy() {
 *         super.onDestroy();
 *         View decorView = getWindow().getWindow().getDecorView();
 *         ViewServer.get(this).removeWindow(decorView);
 *     }
 *
 *     public void onStartInput(EditorInfo attribute, boolean restarting) {
 *         super.onStartInput(attribute, restarting);
 *         View decorView = getWindow().getWindow().getDecorView();
 *         ViewServer.get(this).setFocusedWindow(decorView);
 *     }
 * }
 * </pre>
 */
public class ViewServer implements Runnable {

    private static final String LOG_TAG = ViewServer.class.getSimpleName();

    private static final int VIEW_SERVER_DEFAULT_PORT = 4939;
    private static final int VIEW_SERVER_MAX_CONNECTIONS = 10;
    private static final int BUFFER_SIZE = 8 * 1024;

    private static final String BUILD_TYPE_USER = "user";
    private static final String VALUE_PROTOCOL_VERSION = "4";
    private static final String VALUE_SERVER_VERSION = "4";

    // Protocol commands
    // Returns the protocol version
    private static final String COMMAND_PROTOCOL_VERSION = "PROTOCOL";
    // Returns the server version
    private static final String COMMAND_SERVER_VERSION = "SERVER";
    // Lists all of the available windows in the system
    private static final String COMMAND_WINDOW_MANAGER_LIST = "LIST";
    // Keeps a connection open and notifies when the list of windows changes
    private static final String COMMAND_WINDOW_MANAGER_AUTOLIST = "AUTOLIST";
    // Returns the focused window
    private static final String COMMAND_WINDOW_MANAGER_GET_FOCUS = "GET_FOCUS";

    private static ViewServer server;

    private final int port;
    private final List<WindowListener> windowListeners;
    private final Map<View, String> windows;
    private final ReentrantReadWriteLock windowsLock;
    private final ReentrantReadWriteLock focusLock;

    private ServerSocket serverSocket;
    private Thread thread;
    private ExecutorService threadPool;
    private View focusedWindow;

    /**
     * Returns a unique instance of the ViewServer. This method should only be
     * called valueOf the main thread of your application. The server will have
     * the same lifetime as your process.
     * <p/>
     * If your application does not have the <code>android:debuggable</code>
     * flag set in its manifest, the server returned by this method will
     * be a dummy object that does not do anything. This allows you to use
     * the same code in debug and release versions of your application.
     *
     * @param context A Context used to check whether the application is
     *                debuggable, this can be the application context
     */
    public static ViewServer get(Context context) {
        ApplicationInfo info = context.getApplicationInfo();
        if (BUILD_TYPE_USER.equals(Build.TYPE) &&
                (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0) {
            if (server == null) {
                server = new ViewServer(ViewServer.VIEW_SERVER_DEFAULT_PORT);
            }

            if (!server.isRunning()) {
                try {
                    server.start();
                } catch (IOException e) {
                    Log.d(LOG_TAG, "Error:", e);
                }
            }
        } else {
            server = new NoActionViewServer();
        }

        return server;
    }

    protected ViewServer() {
        this(-1);
    }

    /**
     * Creates a new ViewServer associated with the specified window manager on the
     * specified local port. The server is not started by default.
     *
     * @param port The port for the server to listen to.
     * @see #start()
     */
    protected ViewServer(int port) {
        this.port = port;
        this.windowListeners = new CopyOnWriteArrayList<WindowListener>();
        this.windows = new HashMap<View, String>();
        this.windowsLock = new ReentrantReadWriteLock();
        this.focusLock = new ReentrantReadWriteLock();
    }

    private static boolean writeValue(Socket client, String value) {
        boolean result;
        BufferedWriter out = null;
        try {
            OutputStream clientStream = client.getOutputStream();
            out = new BufferedWriter(new OutputStreamWriter(clientStream), BUFFER_SIZE);
            out.write(value);
            out.write("\n");
            out.flush();
            result = true;
        } catch (Exception e) {
            result = false;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    result = false;
                }
            }
        }
        return result;
    }

    public boolean start() throws IOException {
        if (thread != null) {
            return false;
        }

        thread = new Thread(this, "Local View Server [port=" + port + "]");
        threadPool = Executors.newFixedThreadPool(VIEW_SERVER_MAX_CONNECTIONS);
        thread.start();

        return true;
    }

    public boolean stop() {
        if (thread != null) {
            thread.interrupt();
            if (threadPool != null) {
                try {
                    threadPool.shutdownNow();
                } catch (SecurityException e) {
                    Log.w(LOG_TAG, "Could not stop all view server threads");
                }
            }

            threadPool = null;
            thread = null;

            try {
                serverSocket.close();
                serverSocket = null;
                return true;
            } catch (IOException e) {
                Log.w(LOG_TAG, "Could not close the view server");
            }
        }

        windowsLock.writeLock().lock();
        try {
            windows.clear();
        } finally {
            windowsLock.writeLock().unlock();
        }

        focusLock.writeLock().lock();
        try {
            focusedWindow = null;
        } finally {
            focusLock.writeLock().unlock();
        }

        return false;
    }

    public boolean isRunning() {
        return thread != null && thread.isAlive();
    }

    /**
     * Invoke this method to register a new view hierarchy.
     *
     * @param activity The activity whose view hierarchy/window to register
     * @see #addWindow(View, String)
     * @see #removeWindow(Activity)
     */
    public void addWindow(Activity activity) {
        String name = activity.getTitle().toString();
        if (TextUtils.isEmpty(name)) {
            name = activity.getClass().getCanonicalName() + "/0x" + System.identityHashCode(activity);
        } else {
            name += "(" + activity.getClass().getCanonicalName() + ")";
        }
        addWindow(activity.getWindow().getDecorView(), name);
    }

    /**
     * Invoke this method to unregister a view hierarchy.
     *
     * @param activity The activity whose view hierarchy/window to unregister
     * @see #addWindow(Activity)
     * @see #removeWindow(View)
     */
    public void removeWindow(Activity activity) {
        removeWindow(activity.getWindow().getDecorView());
    }

    /**
     * Invoke this method to register a new view hierarchy.
     *
     * @param view A view that belongs to the view hierarchy/window to register
     * @name name The name of the view hierarchy/window to register
     * @see #removeWindow(View)
     */
    public void addWindow(View view, String name) {
        windowsLock.writeLock().lock();
        try {
            windows.put(view.getRootView(), name);
        } finally {
            windowsLock.writeLock().unlock();
        }
        fireWindowsChangedEvent();
    }

    /**
     * Invoke this method to unregister a view hierarchy.
     *
     * @param view A view that belongs to the view hierarchy/window to unregister
     * @see #addWindow(View, String)
     */
    public void removeWindow(View view) {
        windowsLock.writeLock().lock();
        try {
            windows.remove(view.getRootView());
        } finally {
            windowsLock.writeLock().unlock();
        }
        fireWindowsChangedEvent();
    }

    /**
     * Invoke this method to change the currently focused window.
     *
     * @param activity The activity whose view hierarchy/window hasfocus,
     *                 or null to remove focus
     */
    public void setFocusedWindow(Activity activity) {
        setFocusedWindow(activity.getWindow().getDecorView());
    }

    /**
     * Invoke this method to change the currently focused window.
     *
     * @param view A view that belongs to the view hierarchy/window that has focus,
     *             or null to remove focus
     */
    public void setFocusedWindow(View view) {
        focusLock.writeLock().lock();
        try {
            focusedWindow = view == null ? null : view.getRootView();
        } finally {
            focusLock.writeLock().unlock();
        }
        fireFocusChangedEvent();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port, VIEW_SERVER_MAX_CONNECTIONS, InetAddress.getLocalHost());
        } catch (Exception e) {
            Log.w(LOG_TAG, "Starting ServerSocket error: ", e);
        }

        while (serverSocket != null && Thread.currentThread() == thread) {
            // Any uncaught exception will crash the system process
            try {
                Socket client = serverSocket.accept();
                if (threadPool != null) {
                    threadPool.submit(new ViewServerWorker(client));
                } else {
                    try {
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                Log.w(LOG_TAG, "Connection error: ", e);
            }
        }
    }

    private void fireWindowsChangedEvent() {
        for (WindowListener listener : windowListeners) {
            listener.windowsChanged();
        }
    }

    private void fireFocusChangedEvent() {
        for (WindowListener listener : windowListeners) {
            listener.focusChanged();
        }
    }

    private void addWindowListener(WindowListener listener) {
        if (!windowListeners.contains(listener)) {
            windowListeners.add(listener);
        }
    }

    private void removeWindowListener(WindowListener listener) {
        windowListeners.remove(listener);
    }

    private class ViewServerWorker implements Runnable, WindowListener {

        private final Object[] lock = new Object[0];

        private Socket client;
        private boolean needWindowListUpdate;
        private boolean needFocusedWindowUpdate;

        public ViewServerWorker(Socket client) {
            this.client = client;
            needWindowListUpdate = false;
            needFocusedWindowUpdate = false;
        }

        @Override
        public void run() {
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(client.getInputStream()), 1024);

                final String request = in.readLine();

                String command;
                String parameters;

                int index = request.indexOf(' ');
                if (index == -1) {
                    command = request;
                    parameters = "";
                } else {
                    command = request.substring(0, index);
                    parameters = request.substring(index + 1);
                }

                boolean result;
                if (COMMAND_PROTOCOL_VERSION.equalsIgnoreCase(command)) {
                    result = writeValue(client, VALUE_PROTOCOL_VERSION);
                } else if (COMMAND_SERVER_VERSION.equalsIgnoreCase(command)) {
                    result = writeValue(client, VALUE_SERVER_VERSION);
                } else if (COMMAND_WINDOW_MANAGER_LIST.equalsIgnoreCase(command)) {
                    result = listWindows(client);
                } else if (COMMAND_WINDOW_MANAGER_GET_FOCUS.equalsIgnoreCase(command)) {
                    result = getFocusedWindow(client);
                } else if (COMMAND_WINDOW_MANAGER_AUTOLIST.equalsIgnoreCase(command)) {
                    result = windowManagerAutolistLoop();
                } else {
                    result = windowCommand(client, command, parameters);
                }

                if (!result) {
                    Log.w(LOG_TAG, "An error occurred with the command: " + command);
                }
            } catch (IOException e) {
                Log.w(LOG_TAG, "Connection error: ", e);
            } finally {
                if (in != null) {
                    try {
                        in.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (client != null) {
                    try {
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private boolean windowCommand(Socket client, String command, String parameters) {
            boolean success = true;
            BufferedWriter out = null;

            try {
                // Find the hash code of the window
                int index = parameters.indexOf(' ');
                if (index == -1) {
                    index = parameters.length();
                }
                final String code = parameters.substring(0, index);
                int hashCode = (int) Long.parseLong(code, 16);

                // Extract the command's parameter after the window description
                if (index < parameters.length()) {
                    parameters = parameters.substring(index + 1);
                } else {
                    parameters = "";
                }

                final View window = findWindow(hashCode);
                if (window == null) {
                    return false;
                }

                // call stuff
                final Method dispatch = ViewDebug.class.getDeclaredMethod("dispatchCommand", View.class, String.class, String.class, OutputStream.class);
                dispatch.setAccessible(true);
                dispatch.invoke(null, window, command, parameters,
                        new UncloseableOutputStream(client.getOutputStream()));

                if (!client.isOutputShutdown()) {
                    out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                    out.write("DONE\n");
                    out.flush();
                }

            } catch (Exception e) {
                Log.w(LOG_TAG, String.format("Could not send command %s with parameters %s", command, parameters), e);
                success = false;
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        success = false;
                    }
                }
            }

            return success;
        }

        private View findWindow(int hashCode) {
            if (hashCode == -1) {
                View window = null;
                windowsLock.readLock().lock();
                try {
                    window = focusedWindow;
                } finally {
                    windowsLock.readLock().unlock();
                }
                return window;
            }

            windowsLock.readLock().lock();
            try {
                for (Entry<View, String> entry : windows.entrySet()) {
                    if (System.identityHashCode(entry.getKey()) == hashCode) {
                        return entry.getKey();
                    }
                }
            } finally {
                windowsLock.readLock().unlock();
            }

            return null;
        }

        private boolean listWindows(Socket client) {
            boolean result = true;
            BufferedWriter out = null;

            try {
                windowsLock.readLock().lock();

                OutputStream clientStream = client.getOutputStream();
                out = new BufferedWriter(new OutputStreamWriter(clientStream), BUFFER_SIZE);

                for (Entry<View, String> entry : windows.entrySet()) {
                    out.write(Integer.toHexString(System.identityHashCode(entry.getKey())));
                    out.write(' ');
                    out.append(entry.getValue());
                    out.write('\n');
                }

                out.write("DONE.\n");
                out.flush();
            } catch (Exception e) {
                result = false;
            } finally {
                windowsLock.readLock().unlock();

                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        result = false;
                    }
                }
            }

            return result;
        }

        private boolean getFocusedWindow(Socket client) {
            boolean result = true;
            String focusName = null;

            BufferedWriter out = null;
            try {
                OutputStream clientStream = client.getOutputStream();
                out = new BufferedWriter(new OutputStreamWriter(clientStream), BUFFER_SIZE);

                View focusedWindow = null;

                focusLock.readLock().lock();
                try {
                    focusedWindow = ViewServer.this.focusedWindow;
                } finally {
                    focusLock.readLock().unlock();
                }

                if (focusedWindow != null) {
                    windowsLock.readLock().lock();
                    try {
                        focusName = windows.get(ViewServer.this.focusedWindow);
                    } finally {
                        windowsLock.readLock().unlock();
                    }

                    out.write(Integer.toHexString(System.identityHashCode(focusedWindow)));
                    out.write(' ');
                    out.append(focusName);
                }
                out.write('\n');
                out.flush();
            } catch (Exception e) {
                result = false;
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        result = false;
                    }
                }
            }

            return result;
        }

        public void windowsChanged() {
            synchronized (lock) {
                needWindowListUpdate = true;
                lock.notifyAll();
            }
        }

        public void focusChanged() {
            synchronized (lock) {
                needFocusedWindowUpdate = true;
                lock.notifyAll();
            }
        }

        private boolean windowManagerAutolistLoop() {
            addWindowListener(this);
            BufferedWriter out = null;
            try {
                out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                while (!Thread.interrupted()) {
                    boolean needWindowListUpdate = false;
                    boolean needFocusedWindowUpdate = false;
                    synchronized (lock) {
                        while (!this.needWindowListUpdate && !this.needFocusedWindowUpdate) {
                            lock.wait();
                        }
                        if (this.needWindowListUpdate) {
                            this.needWindowListUpdate = false;
                            needWindowListUpdate = true;
                        }
                        if (this.needFocusedWindowUpdate) {
                            this.needFocusedWindowUpdate = false;
                            needFocusedWindowUpdate = true;
                        }
                    }
                    if (needWindowListUpdate) {
                        out.write("LIST UPDATE\n");
                        out.flush();
                    }
                    if (needFocusedWindowUpdate) {
                        out.write("FOCUS UPDATE\n");
                        out.flush();
                    }
                }
            } catch (Exception e) {
                Log.w(LOG_TAG, "Connection error: ", e);
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        Log.w(LOG_TAG, "Error closing the buffer: ", e);
                    }
                }
                removeWindowListener(this);
            }
            return true;
        }

    }

}
