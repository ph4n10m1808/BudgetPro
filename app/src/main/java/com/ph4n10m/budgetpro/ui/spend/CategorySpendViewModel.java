package com.ph4n10m.budgetpro.ui.spend;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ph4n10m.budgetpro.entity.CategoryCollect;
import com.ph4n10m.budgetpro.entity.CategorySpend;
import com.ph4n10m.budgetpro.repository.CategoryCollectRepository;
import com.ph4n10m.budgetpro.repository.CategorySpendRepository;

import java.util.List;

public class CategorySpendViewModel extends AndroidViewModel {
    private final CategorySpendRepository mCategorySpendRepository;
    private final LiveData<List<CategorySpend>> mAllCategorySpend;

    public CategorySpendViewModel(@NonNull Application application) {
        super(application);
        mCategorySpendRepository = new CategorySpendRepository(application);
        mAllCategorySpend = mCategorySpendRepository.getAllCategorySpend();
    }

    public LiveData<List<CategorySpend>> getmAllCategorySpend() {
        return mAllCategorySpend;
    }

    public void insert(CategorySpend categorySpend) {
        mCategorySpendRepository.insert(categorySpend);
    }

    public void delete(CategorySpend categorySpend) {
        mCategorySpendRepository.delete(categorySpend);
    }

    public void update(CategorySpend categorySpend) {
        mCategorySpendRepository.update(categorySpend);
    }
}