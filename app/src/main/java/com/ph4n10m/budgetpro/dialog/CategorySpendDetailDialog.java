package com.ph4n10m.budgetpro.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ph4n10m.budgetpro.R;
import com.ph4n10m.budgetpro.entity.CategorySpend;
import com.ph4n10m.budgetpro.ui.spend.CategorySpendFragment;
import com.ph4n10m.budgetpro.ui.spend.CategorySpendViewModel;

public class CategorySpendDetailDialog {
    private final CategorySpendViewModel mViewModel;
    private final LayoutInflater mLayoutInflater;
    private final AlertDialog mDialog;
    private final TextView tvId;
    private final TextView tvName;

    public CategorySpendDetailDialog(Context context, CategorySpendFragment fragment, CategorySpend categorySpend) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_detail_category_spend, null);
        tvId = view.findViewById(R.id.tvId);
        tvName = view.findViewById(R.id.tvName);
        tvId.setText("" + categorySpend.catrgory_spend_id);
        tvName.setText(categorySpend.name);

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