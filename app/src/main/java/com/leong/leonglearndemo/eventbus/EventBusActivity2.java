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
import android.view.View;
import android.widget.Button;

import com.leong.leonglearndemo.R;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by LeongAndroid on 2017/8/8.
 */

public class EventBusActivity2 extends AppCompatActivity {
    private Button button = null;
    private Button button2 = null;
    private Button button3 = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventbus_activity2);
        button = (Button)this.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(
                        new MessageEvent("MessageEvent btn clicked"));
            }
        });

        button2 = (Button)this.findViewById(R.id.button3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(
                        new MessageEvent2("MessageEvent2 btn clicked"));
            }
        });

        button3 = (Button)this.findViewById(R.id.button4);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(
                        new MessageEvent3("MessageEvent3 btn clicked"));
            }
        });
    }
}
