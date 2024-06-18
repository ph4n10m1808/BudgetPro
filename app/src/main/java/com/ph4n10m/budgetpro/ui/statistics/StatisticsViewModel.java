package com.ph4n10m.budgetpro.ui.statistics;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ph4n10m.budgetpro.entity.StatisticsCategoryCollect;
import com.ph4n10m.budgetpro.entity.StatisticsCategorySpend;
import com.ph4n10m.budgetpro.repository.CollectRepository;
import com.ph4n10m.budgetpro.repository.SpendRepository;

import java.util.List;

public class StatisticsViewModel extends AndroidViewModel {
    private final CollectRepository mCollectRepository;
    private final SpendRepository mSpendRepository;
    private final LiveData<Float> mTotalCollect;
    private final LiveData<Float> mTotalSpend;
    private final LiveData<List<StatisticsCategoryCollect>> mStatisticsCategoryCollect;
    private final LiveData<List<StatisticsCategorySpend>> mStatisticsCategorySpend;

    public StatisticsViewModel(@NonNull Application application) {
        super(application);

        mCollectRepository = new CollectRepository(application);
        mTotalCollect = mCollectRepository.sumCollect();
        mSpendRepository = new SpendRepository(application);
        mTotalSpend = mSpendRepository.sumSpend();
        mStatisticsCategoryCollect = mCollectRepository.sumByCategoryCollect();
        mStatisticsCategorySpend = mSpendRepository.sumbyCategorySpend();
    }

    public LiveData<List<StatisticsCategoryCollect>> getStatisticsCategoryCollect() {
        return mStatisticsCategoryCollect;
    }

    public LiveData<List<StatisticsCategorySpend>> getStatisticsCategorySpend() {
        return mStatisticsCategorySpend;
    }

    public LiveData<Float> getTotalSpend() {
        return mTotalSpend;
    }

    public LiveData<Float> getTotalCollect() {
        return mTotalCollect;
    }
}
