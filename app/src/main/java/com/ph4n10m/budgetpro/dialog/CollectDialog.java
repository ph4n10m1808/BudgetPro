package com.ph4n10m.budgetpro.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.google.android.material.textfield.TextInputEditText;
import com.ph4n10m.budgetpro.R;
import com.ph4n10m.budgetpro.adapter.CategoryCollectSpinnerAdapter;
import com.ph4n10m.budgetpro.entity.CategoryCollect;
import com.ph4n10m.budgetpro.entity.Collect;
import com.ph4n10m.budgetpro.ui.collect.ApproximatelyCollectFragment;
import com.ph4n10m.budgetpro.ui.collect.ApproximatelyCollectViewModel;

import java.util.List;

public class CollectDialog {
    private final ApproximatelyCollectViewModel mViewModel;
    private final LayoutInflater mLayoutInflater;
    private final AlertDialog mDialog;
    private final TextInputEditText etId;
    private final TextInputEditText etName;
    private final TextInputEditText etAmount;
    private final TextInputEditText etNote;
    private final Spinner spType;

    private final CategoryCollectSpinnerAdapter mAdapter;
    private final boolean mEditMode;

    public CollectDialog(Context context, ApproximatelyCollectFragment fragment, Collect... Collect) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_collect, null);
        etId = view.findViewById(R.id.etId);
        etName = view.findViewById(R.id.etName);
        etAmount = view.findViewById(R.id.etAmount);
        etNote = view.findViewById(R.id.etNote);
        spType = view.findViewById(R.id.spType);
        mAdapter = new CategoryCollectSpinnerAdapter(fragment.getActivity());
        mViewModel.getAllCategoryCollect().observe(fragment.getActivity(), new Observer<List<CategoryCollect>>() {
            @Override
            public void onChanged(List<CategoryCollect> categoryCollects) {
                mAdapter.setList(categoryCollects);
            }
        });
        spType.setAdapter(mAdapter);

        if (Collect != null && Collect.length > 0) {
            etId.setText(String.valueOf(Collect[0].collect_id));
            etName.setText(Collect[0].name);
            etAmount.setText(String.valueOf(Collect[0].money));
            etNote.setText(Collect[0].note);
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
                            Collect collect = new Collect();
                            collect.name = etName.getText().toString();
                            collect.money = Float.parseFloat(etAmount.getText().toString());
                            collect.note = etNote.getText().toString();
                            collect.category_id = ((CategoryCollect) mAdapter.getItem(spType.getSelectedItemPosition())).category_id;

                            if (mEditMode) {
                                collect.collect_id = Integer.parseInt(etId.getText().toString());
                                mViewModel.update(collect);
                                Toast.makeText(context, "Khoản thu đã sửa", Toast.LENGTH_SHORT).show();
                            } else {
                                mViewModel.insert(collect);
                                Toast.makeText(context, "Khoản thu được lưu", Toast.LENGTH_SHORT).show();
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

        // Validate Amount
        String amountText = etAmount.getText().toString();
        if (TextUtils.isEmpty(amountText)) {
            etAmount.setError("Số tiền không được để trống");
            return false;
        }

        try {
            Float.parseFloat(amountText);
        } catch (NumberFormatException e) {
            etAmount.setError("Số tiền không hợp lệ");
            return false;
        }

        return true;
    }

    public void show() {
        mDialog.show();
    }
}
