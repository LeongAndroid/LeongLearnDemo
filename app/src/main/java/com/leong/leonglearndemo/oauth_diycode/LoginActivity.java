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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.leong.leonglearndemo.R;


public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    private Button userInfoButton;
    private EditText nameEdit;
    private EditText pwdEdit;
    UserDataNetwork mUserData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oauth_login);
        mUserData = UserDataNetwork.getInstance();
        loginButton = (Button)this.findViewById(R.id.login);
        nameEdit = (EditText)this.findViewById(R.id.user_name);
        pwdEdit = (EditText)this.findViewById(R.id.user_pwd);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEdit.getText().toString();
                String passwd = pwdEdit.getText().toString();
                mUserData.getToken(name, passwd);
            }
        });
        userInfoButton = (Button)this.findViewById(R.id.users_info);
        userInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserData.getMe();
            }
        });
    }
}
