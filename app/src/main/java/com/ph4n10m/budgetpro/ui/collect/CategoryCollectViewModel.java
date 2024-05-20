package com.ph4n10m.budgetpro.ui.collect;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ph4n10m.budgetpro.entity.CategoryCollect;
import com.ph4n10m.budgetpro.repository.CategoryCollectRepository;

import java.util.List;

public class CategoryCollectViewModel extends AndroidViewModel {
    private final CategoryCollectRepository mCategoryCollectRepository;
    private final LiveData<List<CategoryCollect>> mAllCategoryCollect;

    public CategoryCollectViewModel(@NonNull Application application) {
        super(application);
        mCategoryCollectRepository = new CategoryCollectRepository(application);
        mAllCategoryCollect = mCategoryCollectRepository.getAllCategoryCollect();
    }

    public LiveData<List<CategoryCollect>> getmAllCategoryCollect() {
        return mAllCategoryCollect;
    }

    public void insert(CategoryCollect categoryCollect) {
        mCategoryCollectRepository.insert(categoryCollect);
    }

    public void delete(CategoryCollect categoryCollect) {
        mCategoryCollectRepository.delete(categoryCollect);
    }

    public void update(CategoryCollect categoryCollect) {
        mCategoryCollectRepository.update(categoryCollect);
    }
}