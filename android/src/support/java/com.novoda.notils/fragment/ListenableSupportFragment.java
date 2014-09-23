package com.novoda.notils.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.novoda.notils.caster.Classes;

public class ListenableSupportFragment<L> extends Fragment {

    private L listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = Classes.from(activity);
    }

    protected boolean hasListener() {
        return listener != null;
    }

    protected L getListener() {
        return listener;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
