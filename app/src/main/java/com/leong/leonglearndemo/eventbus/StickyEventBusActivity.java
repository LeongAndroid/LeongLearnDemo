/*
 * Copyright LeongAndroid
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

package com.leong.leonglearndemo.eventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.leong.leonglearndemo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by LeongAndroid on 2017/8/8.
 */

public class StickyEventBusActivity extends AppCompatActivity {

    private int index = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sticky_eventbus);
        findViewById(R.id.post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new MessageEvent("test :: " + index++));
            }
        });
        findViewById(R.id.regist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().register(StickyEventBusActivity.this);
            }
        });

        findViewById(R.id.unregist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().unregister(StickyEventBusActivity.this);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onMessageEventPostThread(MessageEvent messageEvent) {
        Log.e(EventBusActivity1.TAG, "onMessageEventPostThread POSTING : "+messageEvent.getMsg());
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEventMainThread(MessageEvent messageEvent) {
        Log.e(EventBusActivity1.TAG, "onMessageEventMainThread :  "+messageEvent.getMsg());
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND, sticky = true)
    public void onMessageEventBackgroundThread(MessageEvent messageEvent) {
        Log.e(EventBusActivity1.TAG, "onMessageEventBackgroundThread : "+messageEvent.getMsg());
    }

    @Subscribe(threadMode = ThreadMode.ASYNC, sticky = true)
    public void onMessageEventAsync(MessageEvent messageEvent) {
        Log.e(EventBusActivity1.TAG, "onMessageEventAsync : "+ messageEvent.getMsg());
    }


}
