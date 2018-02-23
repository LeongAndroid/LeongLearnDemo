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
package com.leong.leonglearndemo.retrofit2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.leong.leonglearndemo.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by LeongAndroid on 2017/8/24.
 */

public class RetrofitActivity extends AppCompatActivity implements ThemeHelper.ResponseListener{
    public static final String TAG = "RetrofitDemo";
    Map<String, String> map;
    String PID_KEY = "pid";
    String NUMBER_KEY = "num";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrofit2);
        map = new HashMap<>();
        map.put(PID_KEY,"xxx");
        map.put(NUMBER_KEY, "50");
//        testTsRequeset();
        testRequestTheme();
    }

    private void testTsRequeset() {
        TsServices services = TsRetrofitCilent.getRetrofit().create(TsServices.class);
        Call<List<TsResponse>> call = services.getTsList(map);
        Log.d(TAG, "call.toString() = "+call.toString());
        call.enqueue(new Callback<List<TsResponse>>() {
            @Override
            public void onResponse(Call<List<TsResponse>> call, Response<List<TsResponse>> response) {

                Log.d(TAG, "response.isSuccessful() = "+response.isSuccessful());
                List<TsResponse> tsResponseList = response.body();
                for (int i=0; i<tsResponseList.size(); i++) {
                    TsResponse list = tsResponseList.get(i);
                    Log.d(TAG, "list.getKwd() = "+list.getKwd());
                }
                Log.d(TAG, "requestTsData onResponse response.body() = "+response.body());

            }

            @Override
            public void onFailure(Call<List<TsResponse>> call, Throwable t) {
                Log.d(TAG, "t = "+t);
            }

        });
    }


    private void testRequestTheme() {
        ThemeHelper.getThemeHelper().setmListener(this);
        ThemeHelper.getThemeHelper().testThemeRequest();
    }


    @Override
    public void onSuccess(ThemeInfo tsResponse) {
        List<ThemeInfo.ThemeDataBean> themeDataBeanList = tsResponse.getThemeData();
        for (int i=0; i<themeDataBeanList.size(); i++) {
            Log.d(TAG, "themeDataBeanList.get(i) = "+themeDataBeanList.get(i));
        }
    }

    @Override
    public void onFail() {
        Log.d(TAG, "onFail !!");
    }
}
