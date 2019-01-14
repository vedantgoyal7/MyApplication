package com.example.vedantgoyal.myapplication;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.util.Log;

import static android.arch.lifecycle.Lifecycle.State.STARTED;
import static com.example.vedantgoyal.myapplication.MainActivity.TAG;

class MyLocationListener implements LifecycleObserver {
    private boolean enabled = true;
    private Lifecycle lifecycle;
    private Context context;
//    public MyLocationListener(Context context, Lifecycle lifecycle) {
//        this.context=context;
//       this.lifecycle=lifecycle;
//    }
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void hgf(){
        Log.i(TAG, "hgf: ");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void start() {
        if (enabled) {
            // connect
            Log.i(TAG, "start: lifecycle started" );
        }
    }

//    public void enable() {
//        enabled = true;
//        if (lifecycle.getCurrentState().isAtLeast(STARTED)) {
//            // connect if not connected
//            Log.i(TAG, "enable: atleast");
//        }
//    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void stop() {
        // disconnect if connected
        Log.i(TAG, "stop: ");
    }
}