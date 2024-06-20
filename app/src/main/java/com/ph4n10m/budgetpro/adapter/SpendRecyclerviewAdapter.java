package com.ph4n10m.budgetpro.adapter;

import android.annotation.SuppressLint;
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
import com.ph4n10m.budgetpro.entity.Spend;

import java.util.List;

public class SpendRecyclerviewAdapter extends RecyclerView.Adapter<SpendRecyclerviewAdapter.SpendViewHolder> {
    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;
    private final LayoutInflater mLayoutInflater;
    private List<Spend> mList;

    public SpendRecyclerviewAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setOnItemEditClickListener(ItemClickListener itemEditClickListener) {
        SpendRecyclerviewAdapter.itemEditClickListener = itemEditClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        SpendRecyclerviewAdapter.itemViewClickListener = itemViewClickListener;
    }

    @NonNull
    @Override
    public SpendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_spend_item, parent, false);
        return new SpendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpendViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (mList != null) {
            holder.tvName.setText(mList.get(position).name);
            holder.tvAmount.setText(mList.get(position).money + " Đồng");
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

    public Spend getItem(int position) {
        if (mList == null || position >= mList.size()) {
            return null;
        }
        return mList.get(position);
    }

    public void setList(List<Spend> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class SpendViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvAmount;
        public ImageView ivEdit, ivView;
        public CardView cv;
        public int position;

        public SpendViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAmount = itemView.findViewById(R.id.tvAmount);
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
