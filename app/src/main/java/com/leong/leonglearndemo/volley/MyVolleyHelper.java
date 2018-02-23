package com.leong.leonglearndemo.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * 如果你的应用需要经常使用网络，那么创建单例的RequestQueue会更高效
 */

public class MyVolleyHelper {
    private static MyVolleyHelper mInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;


    private MyVolleyHelper(Context context) {
        mContext = context;
    }

    public static synchronized MyVolleyHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MyVolleyHelper(context);
        }
        return mInstance;
    }


    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() 是关键, 它避免了你
            //传递进Activity或BroadcastReceiver导致的内存泄漏
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
