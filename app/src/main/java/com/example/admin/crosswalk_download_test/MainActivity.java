package com.example.admin.crosswalk_download_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.xwalk.core.XWalkInitializer;
import org.xwalk.core.XWalkUpdater;
import org.xwalk.core.XWalkView;

public class MainActivity extends AppCompatActivity
    implements XWalkInitializer.XWalkInitListener, XWalkUpdater.XWalkBackgroundUpdateListener {
    static private String TAG = "MainActivity";

    XWalkInitializer xWalkInitializer;
    XWalkUpdater xWalkUpdater;
    XWalkView xWalkView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        xWalkInitializer = new XWalkInitializer(this, this);
        xWalkInitializer.initAsync();

        setContentView(R.layout.activity_main);


        xWalkView = (XWalkView) findViewById(R.id.xwalkView);
    }

    @Override
    public void onXWalkInitStarted() {
        Log.e(TAG, "onXWalkInitStarted");
    }

    @Override
    public void onXWalkInitCancelled() {
        Log.e(TAG, "onXWalkInitCancelled");
    }

    @Override
    public void onXWalkInitFailed() {
        Log.e(TAG, "onXWalkInitFailed");
        if (xWalkUpdater == null) {
            xWalkUpdater = new XWalkUpdater(this, this);
        }

        xWalkUpdater.updateXWalkRuntime();
    }

    @Override
    public void onXWalkInitCompleted() {
        Log.e(TAG, "onXWalkInitCompleted");
        xWalkView.load("http://192.168.1.153:3000/test", null);
    }

    @Override
    public void onXWalkUpdateStarted() {
        Log.e(TAG, "onXWalkUpdateStarted");
    }

    @Override
    public void onXWalkUpdateProgress(int i) {
        Log.e(TAG, "onXWalkUpdateProgress");
    }

    @Override
    public void onXWalkUpdateCancelled() {
        Log.e(TAG, "onXWalkUpdateCancelled");
    }

    @Override
    public void onXWalkUpdateFailed() {
        Log.e(TAG, "onXWalkUpdateFailed");
    }

    @Override
    public void onXWalkUpdateCompleted() {
        Log.e(TAG, "onXWalkUpdateCompleted");
        xWalkInitializer.initAsync();
    }
}
