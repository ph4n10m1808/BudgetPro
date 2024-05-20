package com.ph4n10m.budgetpro.ui.collect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ph4n10m.budgetpro.R;

public class ApproximatelyCollectFragment extends Fragment {

    private ApproximatelyCollectViewModel mViewModel;

    public static ApproximatelyCollectFragment newInstance() {
        return new ApproximatelyCollectFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_approximately_collect, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ApproximatelyCollectViewModel.class);
        // TODO: Use the ViewModel
    }

}