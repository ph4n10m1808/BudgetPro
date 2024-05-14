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
import com.ph4n10m.budgetpro.entity.CategoryCollect;

import java.util.List;

public class CategoryCollectRecyclerviewAdapter extends RecyclerView.Adapter<CategoryCollectRecyclerviewAdapter.CategoryCollectViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<CategoryCollect> mList;
    public  CategoryCollectRecyclerviewAdapter(Context context)
    {
            mLayoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public CategoryCollectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_category_collect_item,parent,false);
        return new CategoryCollectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryCollectViewHolder holder, int position) {
        if (mList != null){
           holder.tvName.setText(mList.get(position).name);
        }

    }

    @Override
    public int getItemCount() {
        if(mList == null)
        {
            return 0;
        }
        return mList.size();
    }

    public void setList(List<CategoryCollect> mList) {
        this.mList = mList;
    }

    public static class CategoryCollectViewHolder extends RecyclerView.ViewHolder{
        public TextView tvName;
        public ImageView ivEdit,ivView;
        public CardView cardview;
        public CategoryCollectViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ivView = itemView.findViewById(R.id.ivView);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            cardview = (CardView) itemView;
        }
    }
}
