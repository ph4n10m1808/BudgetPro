package com.ph4n10m.budgetpro.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Update;

import com.ph4n10m.budgetpro.dao.AppDatabase;
import com.ph4n10m.budgetpro.dao.CategoryCollectDao;
import com.ph4n10m.budgetpro.entity.CategoryCollect;

import java.util.List;

public class CategoryCollectRepository {
    private final CategoryCollectDao mCategoryCollectDao ;
    private final LiveData<List<CategoryCollect>> mAllCategoryCollect;

    public CategoryCollectRepository(Application application) {
        this.mCategoryCollectDao = AppDatabase.getDatabase(application).categoryCollectDao();
        mAllCategoryCollect = mCategoryCollectDao.findAll();
    }
    public LiveData<List<CategoryCollect>> getAllCategoryCollect()
    {
        return mAllCategoryCollect;
    }
    public void insert(CategoryCollect categoryCollect){
        new InsertAsyncTask(mCategoryCollectDao).execute(categoryCollect);
    }
    public void delete(CategoryCollect categoryCollect){
        new DeleteAsyncTask(mCategoryCollectDao).execute(categoryCollect);
    }
    public void update(CategoryCollect categoryCollect){
        new UpdateAsyncTask(mCategoryCollectDao).execute(categoryCollect);
    }
    class InsertAsyncTask extends AsyncTask<CategoryCollect, Void, Void>{
        private final CategoryCollectDao mCategoryCollectDao;
        public InsertAsyncTask(CategoryCollectDao categoryCollectDao){
            this.mCategoryCollectDao = categoryCollectDao;

        }
        @Override
        protected Void doInBackground(CategoryCollect... categoryCollects) {
            mCategoryCollectDao.insert(categoryCollects[0]);
            return null;
        }
    }
    class DeleteAsyncTask extends AsyncTask<CategoryCollect, Void, Void>{
        private final CategoryCollectDao mCategoryCollectDao;
        public DeleteAsyncTask(CategoryCollectDao categoryCollectDao){
            this.mCategoryCollectDao = categoryCollectDao;

        }
        @Override
        protected Void doInBackground(CategoryCollect... categoryCollects) {
            mCategoryCollectDao.delete(categoryCollects[0]);
            return null;
        }
    }
    class UpdateAsyncTask extends AsyncTask<CategoryCollect, Void, Void>{
        private final CategoryCollectDao mCategoryCollectDao;
        public UpdateAsyncTask(CategoryCollectDao categoryCollectDao){
            this.mCategoryCollectDao = categoryCollectDao;
        }
        @Override
        protected Void doInBackground(CategoryCollect... categoryCollects) {
            mCategoryCollectDao.update(categoryCollects[0]);
            return null;
        }
    }

}
