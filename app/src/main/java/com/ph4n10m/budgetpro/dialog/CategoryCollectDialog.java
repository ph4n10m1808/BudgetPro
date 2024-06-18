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
import com.ph4n10m.budgetpro.entity.CategoryCollect;
import com.ph4n10m.budgetpro.ui.collect.CategoryCollectFragment;
import com.ph4n10m.budgetpro.ui.collect.CategoryCollectViewModel;

public class CategoryCollectDialog {
    private final CategoryCollectViewModel mViewModel;
    private final LayoutInflater mLayoutInflater;
    private final AlertDialog mDialog;
    private final TextInputEditText etId;
    private final TextInputEditText etName;
    private final boolean mEditMode;

    public CategoryCollectDialog(Context context, CategoryCollectFragment fragment, CategoryCollect... categoryCollect) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_category_collect, null);
        etId = view.findViewById(R.id.etId);
        etName = view.findViewById(R.id.etName);
        if (categoryCollect != null && categoryCollect.length > 0) {
            etId.setText(String.valueOf(categoryCollect[0].category_id));
            etName.setText(categoryCollect[0].name);
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
                            CategoryCollect categoryCollect = new CategoryCollect();
                            categoryCollect.name = etName.getText().toString();
                            if (mEditMode) {
                                categoryCollect.category_id = Integer.parseInt(etId.getText().toString());
                                mViewModel.update(categoryCollect);
                                Toast.makeText(context, "Loại thu đã sửa thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                mViewModel.insert(categoryCollect);
                                Toast.makeText(context, "Loại thu được lưu", Toast.LENGTH_SHORT).show();
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
