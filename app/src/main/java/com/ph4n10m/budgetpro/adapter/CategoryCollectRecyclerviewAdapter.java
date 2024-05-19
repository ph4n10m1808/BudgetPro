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

import org.w3c.dom.Text;

import java.util.List;

public class CategoryCollectRecyclerviewAdapter extends RecyclerView.Adapter<CategoryCollectRecyclerviewAdapter.CategoryCollectViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private List<CategoryCollect> mList;
    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;
    public CategoryCollectRecyclerviewAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public  void setOnItemEditClickListener(ItemClickListener itemEditClickListener) {
        CategoryCollectRecyclerviewAdapter.itemEditClickListener = itemEditClickListener;
    }

    public  void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        CategoryCollectRecyclerviewAdapter.itemViewClickListener = itemViewClickListener;
    }

    @NonNull
    @Override
    public CategoryCollectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_category_collect_item, parent,false);
        return new CategoryCollectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryCollectViewHolder holder, int position) {
        if(mList !=null)
        {
            holder.tvName.setText(mList.get(position).name);
            holder.position = position ;
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
    public CategoryCollect getItem(int position){
        if(mList == null || position >= mList.size())
        {
            return null;
        }
        return mList.get(position);
    }
    public void setList(List<CategoryCollect> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class CategoryCollectViewHolder extends RecyclerView.ViewHolder{
        public TextView tvName;
        public ImageView ivEdit, ivView;
        public CardView cv;
        public int position;
        public CategoryCollectViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ivView = itemView.findViewById(R.id.ivView);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            cv = (CardView) itemView;

            ivView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemViewClickListener !=null ){
                        itemViewClickListener.onItemClick(position);
                    }
                }
            });
            ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemEditClickListener != null){
                        itemEditClickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}
