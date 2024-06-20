package com.ph4n10m.budgetpro.ui.collect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ph4n10m.budgetpro.R;
import com.ph4n10m.budgetpro.adapter.CategoryCollectRecyclerviewAdapter;
import com.ph4n10m.budgetpro.adapter.ItemClickListener;
import com.ph4n10m.budgetpro.dialog.CategoryCollectDetailDialog;
import com.ph4n10m.budgetpro.dialog.CategoryCollectDialog;
import com.ph4n10m.budgetpro.entity.CategoryCollect;

import java.util.List;

public class CategoryCollectFragment extends Fragment {

    private RecyclerView mRv;
    private CategoryCollectRecyclerviewAdapter mAdapter;
    private CategoryCollectViewModel mViewModel;

    public static CategoryCollectFragment newInstance() {
        return new CategoryCollectFragment();
    }

    public CategoryCollectViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.getSupportActionBar().setTitle("Nguồn thu nhập");
        }
        return inflater.inflate(R.layout.fragment_category_collect, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView);
        mAdapter = new CategoryCollectRecyclerviewAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
        final CategoryCollectFragment currentFragment = this;
        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                CategoryCollect categoryCollect = mAdapter.getItem(position);
                CategoryCollectDialog dialog = new CategoryCollectDialog(getActivity(), currentFragment, categoryCollect);
                dialog.show();
            }
        });
        mAdapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                CategoryCollect categoryCollect = mAdapter.getItem(position);
                CategoryCollectDetailDialog dialog = new CategoryCollectDetailDialog(getActivity(), currentFragment,
                        categoryCollect);
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
                        CategoryCollect categoryCollect = mAdapter.getItem(position);
                        mViewModel.delete(categoryCollect);
                        Toast.makeText(getActivity(), "Nguồn thu đã xóa", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        helper.attachToRecyclerView(mRv);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CategoryCollectViewModel.class);
        mViewModel.getmAllCategoryCollect().observe(getActivity(), new Observer<List<CategoryCollect>>() {
            @Override
            public void onChanged(List<CategoryCollect> categoryCollects) {
                mAdapter.setList(categoryCollects);
            }
        });
    }


}