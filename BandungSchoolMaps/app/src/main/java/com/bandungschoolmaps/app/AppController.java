package com.bandungschoolmaps.app;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Niko Ahmad Fauzi on 15/10/2017.
 */

public class AppController extends Application {

    private static final String TAG = AppController.class.getSimpleName();

    private static AppController appController;

    private RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        appController = this;
    }

    public static synchronized AppController getInstance() {
        return appController;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(AppController.this);
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> tRequest, String s) {
        tRequest.setTag(TextUtils.isEmpty(s) ? TAG : s);
        getRequestQueue().add(tRequest);
    }

    public <T> void addToRequestQueue(Request<T> tRequest) {
        tRequest.setTag(TAG);
        getRequestQueue().add(tRequest);
    }

    public void cancelPendingRequest(Object o) {
        if (requestQueue != null) {
            requestQueue.cancelAll(o);
        }
    }
}
