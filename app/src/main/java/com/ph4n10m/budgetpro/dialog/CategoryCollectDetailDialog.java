package com.ph4n10m.budgetpro.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ph4n10m.budgetpro.R;
import com.ph4n10m.budgetpro.entity.CategoryCollect;
import com.ph4n10m.budgetpro.ui.collect.CategoryCollectFragment;
import com.ph4n10m.budgetpro.ui.collect.CategoryCollectViewModel;

public class CategoryCollectDetailDialog {
    private final CategoryCollectViewModel mViewModel;
    private final LayoutInflater mLayoutInflater;
    private final AlertDialog mDialog;
    private final TextView tvId;
    private final TextView tvName;

    public CategoryCollectDetailDialog(Context context, CategoryCollectFragment fragment, CategoryCollect categoryCollect) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_detail_category_collect, null);
        tvId = view.findViewById(R.id.tvId);
        tvName = view.findViewById(R.id.tvName);
        tvId.setText("" + categoryCollect.category_id);
        tvName.setText(categoryCollect.name);

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mDialog.dismiss();
                    }
                });
        mDialog = builder.create();
    }

    public void show() {
        mDialog.show();
    }
}