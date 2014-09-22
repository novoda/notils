package com.novoda.notils.viewserver;

import java.io.IOException;
import java.io.OutputStream;

class UncloseableOutputStream extends OutputStream {

    private final OutputStream stream;

    UncloseableOutputStream(OutputStream stream) {
        this.stream = stream;
    }

    public void close() throws IOException {
        // Don't close the stream
    }

    public boolean equals(Object o) {
        return stream.equals(o);
    }

    public void flush() throws IOException {
        stream.flush();
    }

    public int hashCode() {
        return stream.hashCode();
    }

    public String toString() {
        return stream.toString();
    }

    public void write(byte[] buffer, int offset, int count) throws IOException {
        stream.write(buffer, offset, count);
    }

    public void write(byte[] buffer) throws IOException {
        stream.write(buffer);
    }

    public void write(int oneByte) throws IOException {
        stream.write(oneByte);
    }

}
