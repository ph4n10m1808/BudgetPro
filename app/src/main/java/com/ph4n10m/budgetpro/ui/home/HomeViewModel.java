package com.ph4n10m.budgetpro.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ph4n10m.budgetpro.entity.ChartCollect;
import com.ph4n10m.budgetpro.entity.ChartSpend;
import com.ph4n10m.budgetpro.repository.CollectRepository;
import com.ph4n10m.budgetpro.repository.SpendRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private final CollectRepository mCollectRepository;
    private final LiveData<List<ChartCollect>> mChartCollect;
    private final SpendRepository mSpendRepository;
    private final LiveData<List<ChartSpend>> mChartSpend;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        mCollectRepository = new CollectRepository(application);
        mChartCollect = mCollectRepository.getChartCollect();
        mSpendRepository = new SpendRepository(application);
        mChartSpend = mSpendRepository.getChartSpend();
    }

    public LiveData<List<ChartCollect>> getChartCollect() {
        return mChartCollect;
    }

    public LiveData<List<ChartSpend>> getChartSpend() {
        return mChartSpend;
    }
}
