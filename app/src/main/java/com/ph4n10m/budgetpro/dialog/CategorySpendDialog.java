package com.ph4n10m.budgetpro.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.ph4n10m.budgetpro.R;
import com.ph4n10m.budgetpro.entity.CategorySpend;
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
            etId.setText(String.valueOf(categorySpends[0].category_spend_id));
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
                .setPositiveButton("Lưu", null);  // Set to null to override later

        mDialog = builder.create();
        mDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                mDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (validateInput()) {
                            CategorySpend categorySpend = new CategorySpend();
                            categorySpend.name = etName.getText().toString();
                            if (mEditMode) {
                                categorySpend.category_spend_id = Integer.parseInt(etId.getText().toString());
                                mViewModel.update(categorySpend);
                                Toast.makeText(context, "Loại chi đã sửa thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                mViewModel.insert(categorySpend);
                                Toast.makeText(context, "Loại chi đã lưu", Toast.LENGTH_SHORT).show();
                            }

                            mDialog.dismiss();
                        }
                    }
                });
            }
        });
    }

    private boolean validateInput() {
        // Validate Name
        if (TextUtils.isEmpty(etName.getText())) {
            etName.setError("Tên không được để trống");
            return false;
        }

        return true;
    }

    public void show() {
        mDialog.show();
    }
}
