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

import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ThemeHelper {

    public static final String IMEI_PARAM = "imei";
    public static final String PRODUCTBRAND_PARAM = "productBrand";
    public static final String PRODUCTNAME_PARAM = "productName";
    public static final String PRODUCTMANUFACTURER_PARAM = "productManufacturer";
    public static final String CUSTOMBUILDVERSION_PARAM = "customBuildVersion";
    public static final String INTERNALBUILDVERSION_PARAM = "internalBuildVersion";

    public static final String PARAM_mediatype = "mediatype";
    public static final String PARAM_dpi = "dpi";
    public static final String PARAM_resolution = "resolution";
    public static final String PARAM_hwmainkeys = "hwmainkeys";
    public static final String PARAM_apkversion = "apkVersionCode";
    public static final String PARAM_templateversion = "templateversion";

    public static final String PARAM_language = "language";
    public static final String PARAM_region = "region";



    public interface ResponseListener {
        void onSuccess(ThemeInfo tsResponse);
        void onFail();
    }

    ResponseListener mListener;

    public void setmListener(ResponseListener mListener) {
        this.mListener = mListener;
    }

    private static ThemeHelper themeHelper;
    public static ThemeHelper getThemeHelper() {
        if (themeHelper == null) {
            themeHelper = new ThemeHelper();
        }
        return themeHelper;
    }



    public void testThemeRequest() {
        Map map = new HashMap<>();
        map.put(PARAM_region, "cn");
        map.put(PARAM_mediatype, "2");
        map.put("pagenum", "1");
        map.put("pageindex", "0");
        map.put(IMEI_PARAM, "863408022864640");
        map.put(PARAM_language, "zh");
        map.put(PARAM_dpi, "xxhdpi");
        map.put(PARAM_resolution, "1080" + "*" + "1920");
        map.put(PARAM_hwmainkeys, "1");
        map.put(PARAM_apkversion, "1.0");
        map.put(PARAM_templateversion, "1");
        map.put(PRODUCTBRAND_PARAM, /*TDeviceInfo.getBrand()*/"");
        map.put(PRODUCTNAME_PARAM, "");
        map.put(PRODUCTMANUFACTURER_PARAM, "");
        map.put(CUSTOMBUILDVERSION_PARAM, "");
        map.put(INTERNALBUILDVERSION_PARAM, "");
        GetRequest_Interface services = TsRetrofitCilent.getmThemeRetrofit().create(GetRequest_Interface.class);
        Call<ThemeInfo> call = services.getThemeList(map);
        call.enqueue(new Callback<ThemeInfo>() {
            @Override
            public void onResponse(Call<ThemeInfo> call, Response<ThemeInfo> response) {
                Log.d(RetrofitActivity.TAG, "response = "+response);
                Log.d(RetrofitActivity.TAG, "response.isSuccessful() = "+response.isSuccessful());
                if (response.isSuccessful()) {
                    mListener.onSuccess(response.body());
                }else {
                    mListener.onFail();
                }
            }

            @Override
            public void onFailure(Call<ThemeInfo> call, Throwable t) {
                Log.d(RetrofitActivity.TAG, "t = "+t);
                mListener.onFail();
            }
        });
    }

}
