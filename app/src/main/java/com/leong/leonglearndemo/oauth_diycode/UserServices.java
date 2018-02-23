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

import com.leong.leonglearndemo.Constant;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserServices {
    /**
     * 登录获取 Token
     */
    @FormUrlEncoded
    @POST("https://www.diycode.cc/oauth/token")
    Call<Token> getToken(@Field("client_id") String client_id, @Field("client_secret") String client_secret,
            @Field("grant_type") String grant_type, @Field("username") String username,
            @Field("password") String password);

    /**
     * 获取当然登录者的资料
     *
     * @param token 当然登录者的 Token
     */
    @GET("users/me.json") Call<UserDetailInfo> getMe(@Header(Constant.KEY_TOKEN) String token);
}
