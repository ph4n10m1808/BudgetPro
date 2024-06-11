package com.ph4n10m.budgetpro.ui.spend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ph4n10m.budgetpro.R;
import com.ph4n10m.budgetpro.adapter.SpendViewPager2Adapter;

public class SpendFragment extends Fragment {

    private ViewPager2 mVp;
    private TabLayout mT1;


    public SpendFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mVp = view.findViewById(R.id.viewPager2);
        mT1 = view.findViewById(R.id.tabLayout);

        SpendViewPager2Adapter adapter = new SpendViewPager2Adapter(getActivity());
        mVp.setAdapter(adapter);

        new TabLayoutMediator(mT1, mVp, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("Khoản Chi");
                    tab.setIcon(R.drawable.ic_menu_gallery);
                } else {
                    tab.setText("Các loại Khoản Chi");
                    tab.setIcon(R.drawable.ic_menu_gallery);
                }
            }
        }).attach();
    }

    public static SpendFragment newInstance(String param1, String param2) {
        SpendFragment fragment = new SpendFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_spend, container, false);
    }
}