package com.ph4n10m.budgetpro.ui.collect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ph4n10m.budgetpro.R;
import com.ph4n10m.budgetpro.adapter.CollectViewPager2Adapter;


public class CollectFragment extends Fragment {
    public TabLayout mT1;
    private ViewPager2 mVp;

    public CollectFragment() {
    }

    public static CollectFragment newInstance() {
        CollectFragment fragment = new CollectFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mVp = view.findViewById(R.id.viewPager2);
        mT1 = view.findViewById(R.id.tabLayout);
        CollectViewPager2Adapter apapter = new CollectViewPager2Adapter(getActivity());
        mVp.setAdapter(apapter);

        new TabLayoutMediator(mT1, mVp, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("Khoản Thu");
                } else if (position == 1) {
                    tab.setText("Nguồn Thu Nhập");
                }

            }
        }).attach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_collect, container, false);
    }
}