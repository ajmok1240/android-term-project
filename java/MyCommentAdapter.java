package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyCommentAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    List<MyComment> data= new ArrayList<MyComment>();

    public MyCommentAdapter(Context context, List<MyComment> _data) {
        data = _data;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public MyComment getItem(int position) {
        return data.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.comment, null);

        TextView name = (TextView)view.findViewById(R.id.name);
        TextView content = (TextView)view.findViewById(R.id.content);

        name.setText(data.get(position).getName());
        content.setText(data.get(position).getContent());

        return view;
    }
}
