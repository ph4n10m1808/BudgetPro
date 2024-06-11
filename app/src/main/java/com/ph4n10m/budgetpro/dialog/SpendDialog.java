package com.ph4n10m.budgetpro.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.google.android.material.textfield.TextInputEditText;
import com.ph4n10m.budgetpro.R;
import com.ph4n10m.budgetpro.adapter.CategoryCollectSpinnerAdapter;
import com.ph4n10m.budgetpro.adapter.CategorySpendSpinnerAdapter;
import com.ph4n10m.budgetpro.entity.CategoryCollect;
import com.ph4n10m.budgetpro.entity.CategorySpend;
import com.ph4n10m.budgetpro.entity.Collect;
import com.ph4n10m.budgetpro.entity.Spend;
import com.ph4n10m.budgetpro.ui.collect.ApproximatelyCollectFragment;
import com.ph4n10m.budgetpro.ui.collect.ApproximatelyCollectViewModel;
import com.ph4n10m.budgetpro.ui.spend.ApproximatelySpendFragment;
import com.ph4n10m.budgetpro.ui.spend.ApproximatelySpendViewModel;

import java.util.List;

public class SpendDialog {
    private final ApproximatelySpendViewModel mViewModel;
    private final LayoutInflater mLayoutInflater;
    private final AlertDialog mDialog;
    private final TextInputEditText etId;
    private final TextInputEditText etName;
    private final TextInputEditText etAmount;
    private final TextInputEditText etNote;
    private final Spinner spType;

    private final CategorySpendSpinnerAdapter mApadter;
    private final boolean mEditMode;

    public SpendDialog(Context context, ApproximatelySpendFragment fragment, Spend... spends) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_spend, null);
        etId = view.findViewById(R.id.etId);
        etName = view.findViewById(R.id.etName);
        etAmount = view.findViewById(R.id.etAmount);
        etNote = view.findViewById(R.id.etNote);
        spType = view.findViewById(R.id.spType);
        mApadter = new CategorySpendSpinnerAdapter(fragment.getActivity());
        mViewModel.getAllCategorySpend().observe(fragment.getActivity(), new Observer<List<CategorySpend>>() {
            @Override
            public void onChanged(List<CategorySpend> categorySpends) {
                mApadter.setList(categorySpends);
            }
        });
        spType.setAdapter(mApadter);

        if (spends != null && spends.length > 0) {
            etId.setText("" + spends[0].spend_id);
            etName.setText(spends[0].name);
            etAmount.setText(""+spends[0].money);
            etNote.setText(spends[0].note);
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
                        Spend spend = new Spend();
                        spend.name = etName.getText().toString();
                        spend.money = Float.parseFloat(etAmount.getText().toString());
                        spend.note = etNote.getText().toString();
                        spend.category_spend_id = ((CategorySpend) mApadter.getItem(spType.getSelectedItemPosition())).catrgory_spend_id;
                        if (mEditMode) {
                            spend.spend_id = Integer.parseInt(etId.getText().toString());
                            spend.money = Float.parseFloat(etAmount.getText().toString());
                            mViewModel.update(spend);
                        } else {
                            mViewModel.insert(spend);
                            Toast.makeText(context, "Khoản chi được lưu", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        mDialog = builder.create();
    }

    public void show() {
        mDialog.show();
    }
}