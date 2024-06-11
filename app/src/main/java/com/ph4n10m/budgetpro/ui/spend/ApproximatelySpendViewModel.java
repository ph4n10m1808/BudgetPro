package com.ph4n10m.budgetpro.ui.spend;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ph4n10m.budgetpro.entity.CategoryCollect;
import com.ph4n10m.budgetpro.entity.CategorySpend;
import com.ph4n10m.budgetpro.entity.Collect;
import com.ph4n10m.budgetpro.entity.Spend;
import com.ph4n10m.budgetpro.repository.CategoryCollectRepository;
import com.ph4n10m.budgetpro.repository.CategorySpendRepository;
import com.ph4n10m.budgetpro.repository.CollectRepository;
import com.ph4n10m.budgetpro.repository.SpendRepository;

import java.util.List;

public class ApproximatelySpendViewModel extends AndroidViewModel {
    private final SpendRepository mSpendRepository;
    private final LiveData<List<Spend>> mAllSpend;
    private final CategorySpendRepository mCategorySpendReponsitory;
    private final LiveData<List<CategorySpend>> mAllCategory;


    public ApproximatelySpendViewModel(@NonNull Application application) {
        super(application);
        mSpendRepository = new SpendRepository(application);
        mAllSpend = mSpendRepository.getAllSpend();
        mCategorySpendReponsitory = new CategorySpendRepository(application);
        mAllCategory = mCategorySpendReponsitory.getAllCategorySpend();
    }

    public LiveData<List<Spend>> getAllSpend() {
        return mAllSpend;
    }
    public LiveData<List<CategorySpend>> getAllCategorySpend() { return mAllCategory ;}
    public void insert(Spend spend) {
        mSpendRepository.insert(spend);
    }

    public void delete(Spend spend) {
        mSpendRepository.delete(spend);
    }

    public void update(Spend spend) {
        mSpendRepository.update(spend);
    }
}