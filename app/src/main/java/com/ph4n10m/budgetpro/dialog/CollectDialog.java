package com.ph4n10m.budgetpro.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Observable;
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
import com.ph4n10m.budgetpro.adapter.CategoryCollectRecyclerviewAdapter;
import java.util.List;
import com.ph4n10m.budgetpro.repository.CategoryCollectRepository;

public class CollectDialog {
    private final ApproximatelyCollectViewModel mViewModel;
    private final LayoutInflater mLayoutInflater;
    private final AlertDialog mDialog;
    private final TextInputEditText etId;
    private final TextInputEditText etName;
    private final TextInputEditText etAmount;
    private final TextInputEditText etNote;
    private final Spinner spType;

    private final CategoryCollectSpinnerAdapter mApadter;
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
        mApadter = new CategoryCollectSpinnerAdapter(fragment.getActivity());
        mViewModel.getAllCategoryCollect().observe(fragment.getActivity(), new Observer<List<CategoryCollect>>() {
            @Override
            public void onChanged(List<CategoryCollect> categoryCollects) {
                mApadter.setList(categoryCollects);
            }
        });
        spType.setAdapter(mApadter);

        if (Collect != null && Collect.length > 0) {
            etId.setText("" + Collect[0].collect_id);
            etName.setText(Collect[0].name);
            etAmount.setText(""+Collect[0].money);
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
                .setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Collect Collect = new Collect();
                        Collect.name = etName.getText().toString();
                        Collect.money = Float.parseFloat(etAmount.getText().toString());
                        Collect.note = etNote.getText().toString();
                        Collect.category_id = ((CategoryCollect) mApadter.getItem(spType.getSelectedItemPosition())).category_id;
                        if (mEditMode) {
                            Collect.collect_id = Integer.parseInt(etId.getText().toString());
                            Collect.money = Float.parseFloat(etAmount.getText().toString());
                            mViewModel.update(Collect);
                        } else {
                            mViewModel.insert(Collect);
                            Toast.makeText(context, "Khoản thu được lưu", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        mDialog = builder.create();
    }

    public void show() {
        mDialog.show();
    }
}