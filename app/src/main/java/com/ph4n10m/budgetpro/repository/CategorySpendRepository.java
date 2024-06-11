package com.ph4n10m.budgetpro.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ph4n10m.budgetpro.dao.AppDatabase;
import com.ph4n10m.budgetpro.dao.CategoryCollectDao;
import com.ph4n10m.budgetpro.dao.CategorySpendDao;
import com.ph4n10m.budgetpro.entity.CategoryCollect;
import com.ph4n10m.budgetpro.entity.CategorySpend;

import java.util.List;

public class CategorySpendRepository {
    private final CategorySpendDao mCategorySpendDao;
    private final LiveData<List<CategorySpend>> mAllCategorySpend;

    public CategorySpendRepository(Application application) {
        this.mCategorySpendDao = AppDatabase.getDatabase(application).categorySpendDao();
        mAllCategorySpend = mCategorySpendDao.findAll();
    }
    public LiveData<List<CategorySpend>> getAllCategorySpend() {
        return mAllCategorySpend;
    }
    public void insert(CategorySpend categorySpend) {
        new InsertAsyncTask(mCategorySpendDao).execute(categorySpend);
    }

    public void delete(CategorySpend categorySpend) {
        new DeleteAsyncTask(mCategorySpendDao).execute(categorySpend);
    }

    public void update(CategorySpend categorySpend) {
        new UpdateAsyncTask(mCategorySpendDao).execute(categorySpend);
    }

    class InsertAsyncTask extends AsyncTask<CategorySpend, Void, Void> {
        private final CategorySpendDao mCategorySpendDao;

        public InsertAsyncTask(CategorySpendDao categorySpendDao) {
            this.mCategorySpendDao = categorySpendDao;

        }

        @Override
        protected Void doInBackground(CategorySpend... categorySpends) {
            mCategorySpendDao.insert(categorySpends[0]);
            return null;
        }
    }

    class DeleteAsyncTask extends AsyncTask<CategorySpend, Void, Void> {
        private final CategorySpendDao mCategorySpendDao;

        public DeleteAsyncTask(CategorySpendDao categorySpendDao) {
            this.mCategorySpendDao = categorySpendDao;

        }

        @Override
        protected Void doInBackground(CategorySpend... categorySpends) {
            mCategorySpendDao.delete(categorySpends[0]);
            return null;
        }
    }

    class UpdateAsyncTask extends AsyncTask<CategorySpend, Void, Void> {
        private final CategorySpendDao mCategorySpendDao;

        public UpdateAsyncTask(CategorySpendDao categorySpendDao) {
            this.mCategorySpendDao = categorySpendDao;
        }

        @Override
        protected Void doInBackground(CategorySpend... categorySpends) {
            mCategorySpendDao.update(categorySpends[0]);
            return null;
        }
    }

}
