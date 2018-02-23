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

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.leong.greendao.gen.DaoMaster;


/**
 * 数据库字段更新，需要重新实现OpenHelper的onUpgrade方法
 */

public class MyGreenDaoHelper extends DaoMaster.OpenHelper {
    public MyGreenDaoHelper(Context context) {
        super(context, "notes-db",null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        db.execSQL("ALTER TABLE USER ADD COLUMN age");
    }
}
