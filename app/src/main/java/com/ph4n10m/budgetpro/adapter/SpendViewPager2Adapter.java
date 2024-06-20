package com.ph4n10m.budgetpro.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ph4n10m.budgetpro.ui.spend.ApproximatelySpendFragment;
import com.ph4n10m.budgetpro.ui.spend.CategorySpendFragment;

public class SpendViewPager2Adapter extends FragmentStateAdapter {

    public SpendViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = ApproximatelySpendFragment.newInstance();
        } else if (position == 1) {
            fragment = CategorySpendFragment.newInstance();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
