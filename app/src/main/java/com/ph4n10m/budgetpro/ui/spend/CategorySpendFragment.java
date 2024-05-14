package com.ph4n10m.budgetpro.ui.spend;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ph4n10m.budgetpro.R;

public class CategorySpendFragment extends Fragment {

    private CategorySpendViewModel mViewModel;

    public static CategorySpendFragment newInstance() {
        return new CategorySpendFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_spend, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CategorySpendViewModel.class);
        // TODO: Use the ViewModel
    }

}