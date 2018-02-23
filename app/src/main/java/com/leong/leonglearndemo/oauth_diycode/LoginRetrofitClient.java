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

package com.leong.leonglearndemo.oauth_diycode;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginRetrofitClient {

    public static final String BASE_URL = "https://diycode.cc/api/v3/";
    static Retrofit mRetrofit;


    public static Retrofit getRetrofit() {
        if (mRetrofit == null) {
            // 设置 Log 拦截器，可以用于以后处理一些异常情况
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)                // 设置拦截器
                    .retryOnConnectionFailure(true)             // 是否重试
                    .connectTimeout(5, TimeUnit.SECONDS)        // 连接超时事件
                    .readTimeout(5, TimeUnit.SECONDS)           // 读取超时时间
                    .build();

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
