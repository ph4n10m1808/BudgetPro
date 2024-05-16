package com.ph4n10m.budgetpro.ui.collect;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.ph4n10m.budgetpro.R;
import com.ph4n10m.budgetpro.adapter.CategoryCollectRecyclerviewAdapter;
import com.ph4n10m.budgetpro.entity.CategoryCollect;

import java.util.List;

public class CategoryCollectFragment extends Fragment {

    private RecyclerView mRv;
    private CategoryCollectRecyclerviewAdapter mAdapter;
    private CategoryCollectViewModel mViewModel;
    public static CategoryCollectFragment newInstance() {
        return new CategoryCollectFragment();
    }
    public CategoryCollectViewModel getViewModel(){
        return mViewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_collect, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView);
        mAdapter = new CategoryCollectRecyclerviewAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
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