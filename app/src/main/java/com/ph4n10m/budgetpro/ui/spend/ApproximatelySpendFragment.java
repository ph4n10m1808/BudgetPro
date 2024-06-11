package com.ph4n10m.budgetpro.ui.spend;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ph4n10m.budgetpro.R;
import com.ph4n10m.budgetpro.adapter.CollectRecyclerviewAdapter;
import com.ph4n10m.budgetpro.adapter.ItemClickListener;
import com.ph4n10m.budgetpro.adapter.SpendRecyclerviewAdapter;
import com.ph4n10m.budgetpro.dialog.CollectDetailDialog;
import com.ph4n10m.budgetpro.dialog.CollectDialog;
import com.ph4n10m.budgetpro.dialog.SpendDetailDialog;
import com.ph4n10m.budgetpro.dialog.SpendDialog;
import com.ph4n10m.budgetpro.entity.Collect;
import com.ph4n10m.budgetpro.entity.Spend;
import com.ph4n10m.budgetpro.ui.collect.ApproximatelyCollectFragment;
import com.ph4n10m.budgetpro.ui.collect.ApproximatelyCollectViewModel;

import java.util.List;

public class ApproximatelySpendFragment extends Fragment {
    private RecyclerView mRv;
    private SpendRecyclerviewAdapter mAdapter;
    private ApproximatelySpendViewModel mViewModel;

    public static ApproximatelySpendFragment newInstance() {
        return new ApproximatelySpendFragment();
    }
    public ApproximatelySpendViewModel getViewModel(){
        return mViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView);
        mAdapter = new SpendRecyclerviewAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
        final ApproximatelySpendFragment currentFragment = this;
        mAdapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Spend spend = mAdapter.getItem(position);
                SpendDialog dialog = new SpendDialog(getActivity(), currentFragment, spend);
                dialog.show();
            }
        });
        mAdapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Spend spend = mAdapter.getItem(position);
                SpendDetailDialog dialog = new SpendDetailDialog(getActivity(), currentFragment,
                        spend);
                dialog.show();
            }
        });
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Spend spend  = mAdapter.getItem(position);
                        Toast.makeText(getActivity(), "Khoản thu đã được xóa", Toast.LENGTH_SHORT).show();
                        mViewModel.delete(spend);
                    }
                }

        );
        helper.attachToRecyclerView(mRv);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_approximately_spend, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ApproximatelySpendViewModel.class);
        mViewModel.getAllSpend().observe(getActivity(), new Observer<List<Spend>>() {
            @Override
            public void onChanged(List<Spend> spends) {
                mAdapter.setList(spends);
            }
        });
    }

}