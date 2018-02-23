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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.leong.leonglearndemo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by LeongAndroid on 2017/8/8.
 */
public class EventBusActivity1 extends AppCompatActivity {
    public static final String TAG = "EventBusLearn";
    private Button button = null;
    private TextView textView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventbus_activity1);

        EventBus.getDefault().register(this);
        button = (Button)this.findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        EventBusActivity2.class);
                startActivity(intent);
            }
        });

        textView = (TextView)this.findViewById(R.id.tv1);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MessageEvent event) {
        String msg = event.getMsg();
        Log.d(TAG, "onEventMainThread MessageEvent ----> "+msg);
        textView.setText(msg);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MessageEvent2 event) {
        String msg = event.getMsg();
        Log.d(TAG, "onEventMainThread MessageEvent2 ----> "+msg);
        textView.setText(msg);
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEventBackgroundThread(MessageEvent2 event){
        Log.d(TAG, "onEventBackground MessageEvent2 收到了消息：" + event.getMsg());
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEventAsync(MessageEvent2 event){
        Log.d(TAG, "onEventAsync MessageEvent2收到了消息：" + event.getMsg());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MessageEvent3 event) {
        String msg = event.getMsg();
        Log.d(TAG, "onEventMainThread MessageEvent3 ----> "+msg);
        textView.setText(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
