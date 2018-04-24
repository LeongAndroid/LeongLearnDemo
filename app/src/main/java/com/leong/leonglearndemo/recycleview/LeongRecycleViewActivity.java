package com.leong.leonglearndemo.recycleview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.leong.leonglearndemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by issuser on 2018/4/23.
 */

public class LeongRecycleViewActivity extends AppCompatActivity {

    private static String TAG = "LeongRecycleView";

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_layout);
        mRecyclerView = (RecyclerView)this.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        List<String> mList = new ArrayList<>();
        mList.add("liang");
        mList.add("jun");
        mList.add("ming");
        LeongAdapter adapter = new LeongAdapter(this, mList);
        mRecyclerView.setAdapter(adapter);
    }



}
