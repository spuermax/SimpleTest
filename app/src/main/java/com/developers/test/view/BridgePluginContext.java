package com.developers.test.view;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;

/**
 * @Author yinzh
 * @Date 2019/11/3 14:15
 * @Description
 */
public class BridgePluginContext <T extends Activity> extends ContextWrapper {

    private Callback mCallback;

    public BridgePluginContext(T activity) {
        super(activity.getBaseContext());
    }

    public void setActivityResult(Callback callback) {
        this.mCallback = callback;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mCallback == null) {
            return;
        }
        mCallback.onActivityResult(requestCode, resultCode, data);
    }

    public interface Callback {
        void onActivityResult(int requestCode, int resultCode, Intent data);
    }
}
