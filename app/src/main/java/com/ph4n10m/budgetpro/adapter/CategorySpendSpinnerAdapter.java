package com.ph4n10m.budgetpro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ph4n10m.budgetpro.R;
import com.ph4n10m.budgetpro.entity.CategorySpend;

import java.util.List;

public class CategorySpendSpinnerAdapter extends BaseAdapter {
    private final LayoutInflater mLayoutflater;
    private List<CategorySpend> mList;

    public CategorySpendSpinnerAdapter(Context context) {
        mLayoutflater = LayoutInflater.from(context);
    }

    public void setList(List<CategorySpend> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        if (mList == null)
            return null;
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ApproximatelySpendViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutflater.inflate(R.layout.spinner_spend_item, null, false);
            holder = new ApproximatelySpendViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ApproximatelySpendViewHolder) convertView.getTag();
        }
        holder.tvName.setText(mList.get(position).name);
        return convertView;
    }

    public static class ApproximatelySpendViewHolder {
        public TextView tvName;

        public ApproximatelySpendViewHolder(View view) {
            tvName = view.findViewById(R.id.tvName);
        }
    }
}
