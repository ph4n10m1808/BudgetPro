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
import com.ph4n10m.budgetpro.ui.collect.CategoryCollectFragment;
import com.ph4n10m.budgetpro.ui.collect.CategoryCollectViewModel;

public class CategoryCollectDialog {
    private CategoryCollectViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;
    private TextInputEditText etId, etName;
    private boolean mEditMode;

    public CategoryCollectDialog(Context context, CategoryCollectFragment fragment){
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_category_collect, null);
        etId = view.findViewById(R.id.etId);
        etName = view.findViewById(R.id.etName);
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
                        CategoryCollect categoryCollect = new CategoryCollect();
                        categoryCollect.name = etName.getText().toString();
                        mViewModel.insert(categoryCollect);
                        Toast.makeText(context,"Loại thu được lưu", Toast.LENGTH_SHORT).show();
                    }
                });
        mDialog = builder.create();
    }
    public void show(){
        mDialog.show();
    }
}