package com.ph4n10m.budgetpro.ui.collect;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ph4n10m.budgetpro.R;

public class CategoryCollectFragment extends Fragment {

    private CategoryCollectViewModel mViewModel;

    public static CategoryCollectFragment newInstance() {
        return new CategoryCollectFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_collect, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CategoryCollectViewModel.class);
        // TODO: Use the ViewModel
    }

}