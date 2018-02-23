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

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class OkHttpHelper {

    private OkHttpClient mOkHttpClient;
    private static OkHttpHelper mInstance;
    private OkHttpHelper(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS);
        mOkHttpClient = builder.build();
    }

    public static OkHttpHelper getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpHelper.class) {
                if (mInstance == null) {
                    mInstance= new OkHttpHelper();
                }
            }
        }
        return mInstance;
    }
    public OkHttpClient getOkhttpClient() {
        return mOkHttpClient;
    }

}
