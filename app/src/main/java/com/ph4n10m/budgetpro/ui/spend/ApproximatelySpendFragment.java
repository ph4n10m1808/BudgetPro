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

public class ApproximatelySpendFragment extends Fragment {

    private ApproximatelySpendViewModel mViewModel;

    public static ApproximatelySpendFragment newInstance() {
        return new ApproximatelySpendFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_approximately, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ApproximatelySpendViewModel.class);
        // TODO: Use the ViewModel
    }

}