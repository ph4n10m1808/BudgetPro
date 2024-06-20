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
import com.ph4n10m.budgetpro.entity.Collect;

import java.util.List;

public class CollectRecyclerviewAdapter extends RecyclerView.Adapter<CollectRecyclerviewAdapter.CollectViewHolder> {
    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;
    private final LayoutInflater mLayoutInflater;
    private List<Collect> mList;

    public CollectRecyclerviewAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setOnItemEditClickListener(ItemClickListener itemEditClickListener) {
        CollectRecyclerviewAdapter.itemEditClickListener = itemEditClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        CollectRecyclerviewAdapter.itemViewClickListener = itemViewClickListener;
    }

    @NonNull
    @Override
    public CollectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_collect_item, parent, false);
        return new CollectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectViewHolder holder, @SuppressLint("RecyclerView") int position) {
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

    public Collect getItem(int position) {
        if (mList == null || position >= mList.size()) {
            return null;
        }
        return mList.get(position);
    }

    public void setList(List<Collect> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class CollectViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvAmount;
        public ImageView ivEdit, ivView;
        public CardView cv;
        public int position;

        public CollectViewHolder(@NonNull View itemView) {
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
