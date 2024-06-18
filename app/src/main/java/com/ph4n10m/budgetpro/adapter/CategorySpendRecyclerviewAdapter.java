package com.ph4n10m.budgetpro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ph4n10m.budgetpro.R;
import com.ph4n10m.budgetpro.entity.CategorySpend;

import java.util.List;

public class CategorySpendRecyclerviewAdapter extends RecyclerView.Adapter<CategorySpendRecyclerviewAdapter.CategorySpendViewHolder> {
    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;
    private final LayoutInflater mLayoutInflater;
    private List<CategorySpend> mList;

    public CategorySpendRecyclerviewAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setOnItemEditClickListener(ItemClickListener itemEditClickListener) {
        CategorySpendRecyclerviewAdapter.itemEditClickListener = itemEditClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        CategorySpendRecyclerviewAdapter.itemViewClickListener = itemViewClickListener;
    }

    @NonNull
    @Override
    public CategorySpendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_category_spend_item, parent, false);
        return new CategorySpendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategorySpendViewHolder holder, int position) {
        if (mList != null) {
            holder.tvName.setText(mList.get(position).name);
            holder.position = position;
        }
    }

    @Override
    public int getItemCount() {
        if (mList == null) {
            return 0;
        } else {
            return mList.size();
        }
    }

    public CategorySpend getItem(int position) {
        if (mList == null || position >= mList.size()) {
            return null;
        }
        return mList.get(position);
    }

    public void setList(List<CategorySpend> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class CategorySpendViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public ImageView ivEdit, ivView;
        public CardView cv;
        public int position;

        public CategorySpendViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ivView = itemView.findViewById(R.id.ivView);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            cv = (CardView) itemView;

            ivView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemViewClickListener != null) {
                        itemViewClickListener.onItemClick(position);
                    }
                }
            });
            ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemEditClickListener != null) {
                        itemEditClickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}
