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
package com.leong.leonglearndemo.okhttp3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.leong.leonglearndemo.R;
import com.leong.leonglearndemo.retrofit2.ThemeHelper;
import com.leong.leonglearndemo.retrofit2.TsResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * OKHttp 回调方法都不是ui线程，需要自己调用runOnUiThread方法或者handler处理刷新ui界面
 *
 */

public class OkhttpActivity extends AppCompatActivity {
    private static final String TAG = "OkhttpActivity";
    private Button requestButtonGet = null;
    private Button requestButtonPost = null;
    private TextView dataText = null;
    private OkHttpClient mHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okhttp_demo);
        mHelper = OkHttpHelper.getInstance().getOkhttpClient();
        requestButtonGet = (Button)this.findViewById(R.id.okhttp3_Get);
        dataText = (TextView)this.findViewById(R.id.data);
        requestButtonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRequest();
            }
        });
        requestButtonPost = (Button)this.findViewById(R.id.okhttp3_Post);
        requestButtonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postRequest();
            }
        });
    }

    private void getRequest() {
        Request requestBuilder = new Request.Builder().url("http://ts.mobile.sogou.com/query?pid=?&num=15").build();
        Call call =  mHelper.newCall(requestBuilder);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String data = response.body().string();
                    List<TsResponse> tsResponseList = new ArrayList<TsResponse>();
                    try {
                        JSONArray array = new JSONArray(data);
                        for (int i=0; i<array.length(); i++) {
                            JSONObject myjObject = array.getJSONObject(i);
                            String kwd = myjObject.getString("kwd");
                            String url = myjObject.getString("url");
                            TsResponse tsRespons = new TsResponse();
                            tsRespons.setKwd(kwd);
                            tsRespons.setUrl(url);
                            tsResponseList.add(tsRespons);
                        }
                        final List<TsResponse> lastList = tsResponseList;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d(TAG, "refresh ui.: "+lastList.size());
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    Log.d(TAG, "onResponse no data.");
                }
            }
        });
    }

    private void postRequest() {
        RequestBody formBody = new FormBody.Builder()
                .add(ThemeHelper.PARAM_region, "cn")
                .add(ThemeHelper.PARAM_mediatype, "2")
                .add("pagenum", "1")
                .add("pageindex", "0")
                .add(ThemeHelper.IMEI_PARAM, "863408022864640")
                .add(ThemeHelper.PARAM_language, "zh")
                .add(ThemeHelper.PARAM_dpi, "xxhdpi")
                .add(ThemeHelper.PARAM_resolution, "1080" + "*" + "1920")
                .add(ThemeHelper.PARAM_hwmainkeys, "1")
                .add(ThemeHelper.PARAM_apkversion, "1.0")
                .add(ThemeHelper.PARAM_templateversion, "1")
                .add(ThemeHelper.PRODUCTBRAND_PARAM, "")
                .add(ThemeHelper.PRODUCTNAME_PARAM, "")
                .add(ThemeHelper.PRODUCTMANUFACTURER_PARAM, "")
                .add(ThemeHelper.CUSTOMBUILDVERSION_PARAM, "")
                .add(ThemeHelper.INTERNALBUILDVERSION_PARAM, "")
                .build();
        final Request request = new Request.Builder()
                .url("http://xxxx")
                .post(formBody)
                .build();
        Call call = mHelper.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "postRequest onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObj = new JSONObject(response.body().string());
                        Log.d(TAG, "jsonObj = "+jsonObj.toString());
                        JSONArray data = jsonObj.getJSONArray("themeData");
                        ThemeInfo themeInfo = null;
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject item = (JSONObject) data.get(i);
                            Gson gson = new GsonBuilder().create();
                            themeInfo = gson.fromJson(item.toString(), ThemeInfo.class);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d(TAG, "postRequest e. = "+e);
                    }
                }else {
                    Log.d(TAG, "postRequest no data.");
                }
            }

        });
    }

}
