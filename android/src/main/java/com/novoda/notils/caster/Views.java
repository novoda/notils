package com.novoda.notils.caster;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

public final class Views {

    private Views() {
    }

    /**
     * @deprecated Compile against API 26 and use {@link View#findViewById(int)}.
     */
    @SuppressWarnings({"unchecked", "UnusedDeclaration"})
    @Deprecated
    public static <T extends View> T findById(View view, int id) {
        return (T) view.findViewById(id);
    }

    /**
     * @deprecated Compile against API 26 and use {@link View#findViewById(int)}.
     */
    @SuppressWarnings({"unchecked", "UnusedDeclaration"})
    public static <T extends View> T findById(Activity activity, int id) {
        return (T) activity.findViewById(id);
    }

}

