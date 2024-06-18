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
import com.ph4n10m.budgetpro.entity.StatisticsCategorySpend;

import java.util.List;

public class StatisticsCategorySpendRecyclerViewAdapter extends RecyclerView.Adapter<StatisticsCategorySpendRecyclerViewAdapter.StatisticsCategoryViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private List<StatisticsCategorySpend> mList;

    public StatisticsCategorySpendRecyclerViewAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public StatisticsCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_statistics_categoryspend, parent, false);
        return new StatisticsCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatisticsCategoryViewHolder holder, int position) {
        if (mList != null) {
//            StatisticsCategoryCollect currentItem = mList.get(position);
//            holder.tvNameCategoryCollect.setText(currentItem.name());  // Set actual data
//            holder.etTotalCategorySpend.setText(String.valueOf(currentItem.total()));  // Set actual data
            holder.tvNameCategorySpend.setText(mList.get(position).name);
            holder.etTotalCategorySpend.setText(mList.get(position).total + " Đồng");
        } else {
            holder.tvNameCategorySpend.setText("No Data");
            holder.etTotalCategorySpend.setText("N/A");
        }
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    public void setList(List<StatisticsCategorySpend> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public static class StatisticsCategoryViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNameCategorySpend;
        public EditText etTotalCategorySpend;

        public StatisticsCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameCategorySpend = itemView.findViewById(R.id.tvNameCategorySpend);
            etTotalCategorySpend = itemView.findViewById(R.id.etTotalCategory);
        }
    }
}
