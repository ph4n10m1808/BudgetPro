package com.ph4n10m.budgetpro.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ph4n10m.budgetpro.ui.collect.ApproximatelyCollectFragment;
import com.ph4n10m.budgetpro.ui.collect.CategoryCollectFragment;

public class CollectViewPager2Adapter extends FragmentStateAdapter {

    public CollectViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = ApproximatelyCollectFragment.newInstance();
        } else if (position == 1) {
            fragment = CategoryCollectFragment.newInstance();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
