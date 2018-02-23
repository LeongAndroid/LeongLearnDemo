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
package com.leong.leonglearndemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.leong.leonglearndemo.eventbus.EventBusActivity1;
import com.leong.leonglearndemo.greendao.GreenDaoActivity;
import com.leong.leonglearndemo.oauth_diycode.LoginActivity;
import com.leong.leonglearndemo.okhttp3.OkhttpActivity;
import com.leong.leonglearndemo.retrofit2.RetrofitActivity;
import com.leong.leonglearndemo.rxjava.HelloRxjava;
import com.leong.leonglearndemo.volley.VolleyActivity;

import java.lang.reflect.InvocationTargetException;

import retrofit2.Retrofit;


/**
 * Created by LeongAndroid on 2017/8/8.
 */

public class MainActivity extends AppCompatActivity {
    private Button eventBusButton = null;
    private Button rxjavaButton = null;
    private Button retrofitButton = null;
    private Button oauthButton = null;
    private Button greenDaoButton = null;
    private Button okhttpButton = null;
    private Button volleyButton = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        eventBusButton = (Button)this.findViewById(R.id.eventbus);
        eventBusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, EventBusActivity1.class);
                startActivity(intent);
            }
        });
        rxjavaButton = (Button)this.findViewById(R.id.rxjava);
        rxjavaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, HelloRxjava.class);
                startActivity(intent);
            }
        });

        retrofitButton = (Button)this.findViewById(R.id.retrofit2);
        retrofitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, RetrofitActivity.class);
                startActivity(intent);
            }
        });

        oauthButton = (Button)this.findViewById(R.id.diycode_oauth);
        oauthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        greenDaoButton = (Button)this.findViewById(R.id.greendao);
        greenDaoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, GreenDaoActivity.class);
                startActivity(intent);
            }
        });

        okhttpButton = (Button)this.findViewById(R.id.okhttp3);
        okhttpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, OkhttpActivity.class);
                startActivity(intent);
            }
        });

        volleyButton = (Button)this.findViewById(R.id.volley);
        volleyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, VolleyActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
