package com.leong.leonglearndemo.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leong.leonglearndemo.R;

import java.util.List;

/**
 * Created by LeongAndroid on 2018/4/23.
 */

public class LeongAdapter extends RecyclerView.Adapter{

    private List<String> mList;
    private final LayoutInflater mLayoutInflater;
    public LeongAdapter(Context context, List<String> list) {
        mLayoutInflater = LayoutInflater.from(context);
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("liangjunming", "onCreateViewHolder ");
        View view = mLayoutInflater.inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d("liangjunming", "onBindViewHolder position = "+position);
        ViewHolder viewHolder = (ViewHolder)holder;
        Log.d("liangjunming", "mList.get(position) = "+mList.get(position));
        viewHolder.mText.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d("liangjunming", "getItemCount mList.size() = "+mList.size());
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(mList.size() <= 0){
            return -1;
        }
        return super.getItemViewType(position);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mText;
        public ViewHolder(View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(R.id.text);
        }
    }


}
