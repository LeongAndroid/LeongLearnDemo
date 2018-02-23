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

package com.leong.leonglearndemo.greendao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.leong.greendao.gen.DaoSession;
import com.leong.greendao.gen.UserDao;
import com.leong.leonglearndemo.LeongLearnApplication;
import com.leong.leonglearndemo.R;

import java.util.List;


public class GreenDaoActivity extends AppCompatActivity {
    private static final String TAG = "GreenDaoActivityDemo";
    private Button mAdd;
    private Button mUpdate;
    private Button mDel;
    private Button mQuery;

    DaoSession mDaoSession;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.greendao_test);
        mAdd = (Button)this.findViewById(R.id.add_data);
        mUpdate = (Button)this.findViewById(R.id.update_data);
        mDel = (Button)this.findViewById(R.id.del_data);
        mQuery = (Button)this.findViewById(R.id.query);
        mDaoSession = ((LeongLearnApplication)getApplication()).getDaoSession();

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user1 = new User(null, "name1", 11);
                User user2 = new User(null, "name2", 12);
                User user3 = new User(null, "name3", 13);
                User user4 = new User(null, "name4", 15);
                mDaoSession.getUserDao().insert(user1);
                mDaoSession.getUserDao().insert(user2);
                mDaoSession.getUserDao().insert(user3);
                mDaoSession.getUserDao().insert(user4);

            }
        });

        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<User> userList = mDaoSession.getUserDao().queryBuilder().where(UserDao.Properties.Name.eq("name1")).build().list();
                for (int i=0 ;i<userList.size();i++) {
                    User user = userList.get(i);
                    user.setName("nameK");
                    mDaoSession.getUserDao().update(user);
                }
            }
        });

        mDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<User> userList2 = mDaoSession.getUserDao().queryBuilder().where(UserDao.Properties.Id.eq(2)).build().list();
                for (User user5 : userList2) {
                    mDaoSession.getUserDao().delete(user5);
                }

            }
        });

        mQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<User> userList = mDaoSession.getUserDao().queryBuilder().build().list();
                for (User user : userList) {
                    Log.i(TAG, "user = "+user.toString());
                }
            }
        });



    }
}
