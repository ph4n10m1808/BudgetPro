package com.ph4n10m.budgetpro.ui.collect;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ph4n10m.budgetpro.entity.CategoryCollect;
import com.ph4n10m.budgetpro.entity.Collect;
import com.ph4n10m.budgetpro.repository.CategoryCollectRepository;
import com.ph4n10m.budgetpro.repository.CollectRepository;

import java.util.List;

public class ApproximatelyCollectViewModel extends AndroidViewModel {
    private final CollectRepository mCollectRepository;
    private final LiveData<List<Collect>> mAllCollect;
    private final CategoryCollectRepository mCategoryCollectReponsitory;
    private final LiveData<List<CategoryCollect>> mAllCategory;


    public ApproximatelyCollectViewModel(@NonNull Application application) {
        super(application);
        mCollectRepository = new CollectRepository(application);
        mAllCollect = mCollectRepository.getAllCollect();
        mCategoryCollectReponsitory = new CategoryCollectRepository(application);
        mAllCategory = mCategoryCollectReponsitory.getAllCategoryCollect();
    }

    public LiveData<List<Collect>> getAllCollect() {
        return mAllCollect;
    }

    public LiveData<List<CategoryCollect>> getAllCategoryCollect() {
        return mAllCategory;
    }

    public LiveData<String> getName(int id) {
        return mCategoryCollectReponsitory.getName(id);
    }

    public void insert(Collect collect) {
        mCollectRepository.insert(collect);
    }

    public void delete(Collect collect) {
        mCollectRepository.delete(collect);
    }

    public void update(Collect collect) {
        mCollectRepository.update(collect);
    }
}