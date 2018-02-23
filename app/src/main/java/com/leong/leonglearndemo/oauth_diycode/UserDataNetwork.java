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

import android.util.Log;

import com.leong.leonglearndemo.Constant;
import com.leong.leonglearndemo.LeongLearnApplication;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserDataNetwork {
    private static final String TAG = "UserDataNetwork";
    private UserServices mUserServices;
    private static UserDataNetwork userDataNetwork = new UserDataNetwork();
    private UserDataNetwork() {
        mUserServices = LoginRetrofitClient.getRetrofit().create(UserServices.class);
    }

    public static UserDataNetwork getInstance() {
        return userDataNetwork;
    }

    public void getToken(String username, String password) {
        Call<Token> call = mUserServices.getToken(Constant.client_id,
                Constant.client_secret,Constant.VALUE_GRANT_TYPE_PASSWORD, username, password);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful()) {
                    Token token = response.body();
                    Log.d(TAG, "token = "+token);
                    if (token != null) {
                        Constant.VALUE_TOKEN = token.getAccessToken();
                    }
                }else {
                    Log.d(TAG, "fail......");
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Log.d(TAG, "onFailure......t = "+t);
            }
        });
    }

    public void getMe() {
        Call<UserDetailInfo> call =
                mUserServices.getMe(Constant.VALUE_TOKEN_PREFIX + Constant.VALUE_TOKEN);
        call.enqueue(new Callback<UserDetailInfo>() {
            @Override public void onResponse(Call<UserDetailInfo> call,
                                             retrofit2.Response<UserDetailInfo> response) {
                if (response.isSuccessful()) {
                    UserDetailInfo userDetailInfo = response.body();
                    Log.d(TAG, "userDetailInfo: " + userDetailInfo);
                } else {
                    Log.e(TAG, "getMe STATUS: " + response.code());
                }
            }

            @Override public void onFailure(Call<UserDetailInfo> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

}
