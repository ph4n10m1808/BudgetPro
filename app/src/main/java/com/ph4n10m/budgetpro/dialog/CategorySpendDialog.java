package com.ph4n10m.budgetpro.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.ph4n10m.budgetpro.R;
import com.ph4n10m.budgetpro.entity.CategoryCollect;
import com.ph4n10m.budgetpro.entity.CategorySpend;
import com.ph4n10m.budgetpro.ui.collect.CategoryCollectFragment;
import com.ph4n10m.budgetpro.ui.collect.CategoryCollectViewModel;
import com.ph4n10m.budgetpro.ui.spend.CategorySpendFragment;
import com.ph4n10m.budgetpro.ui.spend.CategorySpendViewModel;

public class CategorySpendDialog {
    private final CategorySpendViewModel mViewModel;
    private final LayoutInflater mLayoutInflater;
    private final AlertDialog mDialog;
    private final TextInputEditText etId;
    private final TextInputEditText etName;
    private final boolean mEditMode;

    public CategorySpendDialog(Context context, CategorySpendFragment fragment, CategorySpend... categorySpends) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_category_spend, null);
        etId = view.findViewById(R.id.etId);
        etName = view.findViewById(R.id.etName);
        if (categorySpends != null && categorySpends.length > 0) {
            etId.setText("" + categorySpends[0].catrgory_spend_id);
            etName.setText(categorySpends[0].name);
            mEditMode = true;
        } else {
            mEditMode = false;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mDialog.dismiss();
                    }
                })
                .setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CategorySpend categorySpend = new CategorySpend();
                        categorySpend.name = etName.getText().toString();
                        if (mEditMode) {
                            categorySpend.catrgory_spend_id = Integer.parseInt(etId.getText().toString());
                            mViewModel.update(categorySpend);
                        } else {
                            mViewModel.insert(categorySpend);
                            Toast.makeText(context, "Loại chi được lưu", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        mDialog = builder.create();
    }

    public void show() {
        mDialog.show();
    }
}