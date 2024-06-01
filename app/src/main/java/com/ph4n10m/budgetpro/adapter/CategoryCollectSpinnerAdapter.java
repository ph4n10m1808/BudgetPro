package com.ph4n10m.budgetpro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ph4n10m.budgetpro.R;
import com.ph4n10m.budgetpro.entity.CategoryCollect;
import com.ph4n10m.budgetpro.entity.Collect;

import java.util.List;

public class CategoryCollectSpinnerAdapter extends BaseAdapter {
    private List<CategoryCollect> mList;
    private LayoutInflater mLayoutflater;
    public CategoryCollectSpinnerAdapter(Context context){
        mLayoutflater = LayoutInflater.from(context);
    }

    public void setList(List<CategoryCollect> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(mList==null)
            return 0;
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        if(mList == null)
            return null;
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ApproximatelyCollectViewHolder holder;
        if(convertView == null)
        {
            convertView = mLayoutflater.inflate(R.layout.spinner_collect_item, null,false);
            holder = new ApproximatelyCollectViewHolder(convertView);
            convertView.setTag(holder);
        }
        else{
            holder = (ApproximatelyCollectViewHolder) convertView.getTag();
        }
        holder.tvMame.setText(mList.get(position).name);
        return convertView ;
    }
    public static class ApproximatelyCollectViewHolder{
        public TextView tvMame;
        public ApproximatelyCollectViewHolder(View view){
            tvMame = view.findViewById(R.id.tvName);
        }
    }
}
