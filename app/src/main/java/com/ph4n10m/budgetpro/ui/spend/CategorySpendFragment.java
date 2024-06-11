package com.ph4n10m.budgetpro.ui.spend;

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
import com.ph4n10m.budgetpro.adapter.CategoryCollectRecyclerviewAdapter;
import com.ph4n10m.budgetpro.adapter.CategorySpendRecyclerviewAdapter;
import com.ph4n10m.budgetpro.adapter.ItemClickListener;
import com.ph4n10m.budgetpro.dialog.CategoryCollectDetailDialog;
import com.ph4n10m.budgetpro.dialog.CategoryCollectDialog;
import com.ph4n10m.budgetpro.dialog.CategorySpendDetailDialog;
import com.ph4n10m.budgetpro.dialog.CategorySpendDialog;
import com.ph4n10m.budgetpro.entity.CategoryCollect;
import com.ph4n10m.budgetpro.entity.CategorySpend;
import com.ph4n10m.budgetpro.ui.collect.CategoryCollectFragment;
import com.ph4n10m.budgetpro.ui.collect.CategoryCollectViewModel;

import java.util.List;

public class CategorySpendFragment extends Fragment {

    private RecyclerView mRv;
    private CategorySpendRecyclerviewAdapter mAdapter;
    private CategorySpendViewModel mViewModel;

    public static CategorySpendFragment newInstance() {
        return new CategorySpendFragment();
    }

    public CategorySpendViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_spend, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView);
        mAdapter = new CategorySpendRecyclerviewAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
        final CategorySpendFragment currentFragment = this;
        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                CategorySpend categorySpend = mAdapter.getItem(position);
                CategorySpendDialog dialog = new CategorySpendDialog(getActivity(), currentFragment, categorySpend);
                dialog.show();
            }
        });
        mAdapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                CategorySpend categorySpend = mAdapter.getItem(position);
                CategorySpendDetailDialog dialog = new CategorySpendDetailDialog(getActivity(), currentFragment,
                        categorySpend);
                dialog.show();
            }
        });
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
                ) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        CategorySpend categorySpend = mAdapter.getItem(position);

                        Toast.makeText(getActivity(), "Loại chi đã được xóa", Toast.LENGTH_SHORT).show();
                        mViewModel.delete(categorySpend);
                    }
                }
        );
        helper.attachToRecyclerView(mRv);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CategorySpendViewModel.class);
        mViewModel.getmAllCategorySpend().observe(getActivity(), new Observer<List<CategorySpend>>() {
            @Override
            public void onChanged(List<CategorySpend> categorySpends) {
                mAdapter.setList(categorySpends);
            }
        });
    }

}