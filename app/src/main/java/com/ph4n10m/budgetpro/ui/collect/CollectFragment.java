package com.ph4n10m.budgetpro.ui.collect;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ph4n10m.budgetpro.R;
import com.ph4n10m.budgetpro.adapter.CollectViewPager2Adapter;


public class CollectFragment extends Fragment {
    private ViewPager2 mVp;
    public TabLayout mT1;

    public CollectFragment() {
        // Required empty public constructor
    }
    public static CollectFragment newInstance(String param1, String param2) {
        CollectFragment fragment = new CollectFragment();
        return fragment;
    }
    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mVp = view.findViewById(R.id.viewPager2);
        mT1 = view.findViewById(R.id.tabLayout);

        CollectViewPager2Adapter apapter = new CollectViewPager2Adapter(getActivity());
        mVp.setAdapter(apapter);

        new TabLayoutMediator(mT1, mVp, new TabLayoutMediator.TabConfigurationStrategy(){
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position ==0)
                {
                    tab.setIcon(R.drawable.ic_menu_camera);
                    tab.setText("Khoản Thu");
                }
                else {
                    tab.setIcon(R.drawable.ic_menu_camera);
                    tab.setText("Nguồn Thu Nhập");
                }

            }
        }).attach();
    }
//
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collect, container, false);
    }
}