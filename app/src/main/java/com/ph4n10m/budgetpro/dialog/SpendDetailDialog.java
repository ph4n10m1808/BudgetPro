package com.ph4n10m.budgetpro.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ph4n10m.budgetpro.R;
import com.ph4n10m.budgetpro.entity.Collect;
import com.ph4n10m.budgetpro.entity.Spend;
import com.ph4n10m.budgetpro.ui.collect.ApproximatelyCollectFragment;
import com.ph4n10m.budgetpro.ui.collect.ApproximatelyCollectViewModel;
import com.ph4n10m.budgetpro.ui.spend.ApproximatelySpendFragment;
import com.ph4n10m.budgetpro.ui.spend.ApproximatelySpendViewModel;

public class SpendDetailDialog {
    private final ApproximatelySpendViewModel mViewModel;
    private final LayoutInflater mLayoutInflater;
    private final AlertDialog mDialog;
    private final TextView tvId;
    private final TextView tvName;
    private final TextView tvCategory;
    private final TextView tvMoney;
    private final TextView tvNote;

    public SpendDetailDialog(Context context, ApproximatelySpendFragment fragment, Spend spend ) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_detail_spend, null);
        tvId = view.findViewById(R.id.tvId);
        tvCategory = view.findViewById(R.id.tvCategory);
        tvMoney = view.findViewById(R.id.tvMoney);
        tvNote = view.findViewById(R.id.tvNote);
        tvName = view.findViewById(R.id.tvName);
        tvId.setText("" + spend.spend_id);
        tvName.setText(spend.name);
        tvCategory.setText("" + spend.category_spend_id);
        tvMoney.setText(spend.money + "Đồng");
        tvNote.setText(spend.note);

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