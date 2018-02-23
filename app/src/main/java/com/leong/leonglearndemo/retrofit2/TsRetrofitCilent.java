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

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TsRetrofitCilent {
    private static final String BASEURL = "http://ts.mobile.sogou.com/";
    public static final String THEMEBASEURL = "http://xxxx";
    static Retrofit mRetrofit;
    static Retrofit mThemeRetrofit;

    public static Retrofit getRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    public static Retrofit getmThemeRetrofit() {
        if (mThemeRetrofit == null) {
            mThemeRetrofit = new Retrofit.Builder()
                    .baseUrl(THEMEBASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mThemeRetrofit;
    }


}
