package com.ph4n10m.budgetpro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ph4n10m.budgetpro.R;
import com.ph4n10m.budgetpro.entity.StatisticsCategoryCollect;

import java.util.List;

public class StatisticsCategoryCollectRecyclerViewAdapter extends RecyclerView.Adapter<StatisticsCategoryCollectRecyclerViewAdapter.StatisticsCategoryViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private List<StatisticsCategoryCollect> mList;

    public StatisticsCategoryCollectRecyclerViewAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public StatisticsCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_statistics_categorycollect, parent, false);
        return new StatisticsCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatisticsCategoryViewHolder holder, int position) {
        if (mList != null) {
            holder.tvNameCategoryCollect.setText(mList.get(position).name);
            holder.etTotalCategoryCollect.setText(mList.get(position).total + " Đồng");
        } else {
            holder.tvNameCategoryCollect.setText("No Data");
            holder.etTotalCategoryCollect.setText("N/A");
        }
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    public void setList(List<StatisticsCategoryCollect> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public static class StatisticsCategoryViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNameCategoryCollect;
        public EditText etTotalCategoryCollect;

        public StatisticsCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameCategoryCollect = itemView.findViewById(R.id.tvNameCategoryCollect);
            etTotalCategoryCollect = itemView.findViewById(R.id.etTotalCategory);
        }
    }
}
