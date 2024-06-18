package com.ph4n10m.budgetpro.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ph4n10m.budgetpro.dao.AppDatabase;
import com.ph4n10m.budgetpro.dao.CollectDao;
import com.ph4n10m.budgetpro.entity.ChartCollect;
import com.ph4n10m.budgetpro.entity.Collect;
import com.ph4n10m.budgetpro.entity.StatisticsCategoryCollect;

import java.util.List;

public class CollectRepository {
    private final CollectDao mCollectDao;
    private final LiveData<List<Collect>> mAllCollect;

    public CollectRepository(Application application) {
        this.mCollectDao = AppDatabase.getDatabase(application).collectDao();
        mAllCollect = mCollectDao.findAll();
    }

    public LiveData<List<Collect>> getAllCollect() {
        return mAllCollect;
    }

    public LiveData<Float> sumCollect() {
        return mCollectDao.sumCollect();
    }

    public LiveData<List<StatisticsCategoryCollect>> sumByCategoryCollect() {
        return mCollectDao.sumByCategoryCollect();
    }

    public LiveData<List<ChartCollect>> getChartCollect() {
        return mCollectDao.getChartCollect();
    }

    public void insert(Collect collect) {
        new InsertAsyncTask(mCollectDao).execute(collect);
    }

    public void delete(Collect collect) {
        new DeleteAsyncTask(mCollectDao).execute(collect);
    }

    public void update(Collect collect) {
        new UpdateAsyncTask(mCollectDao).execute(collect);
    }

    class InsertAsyncTask extends AsyncTask<Collect, Void, Void> {
        private final CollectDao mCollectDao;

        public InsertAsyncTask(CollectDao collectDao) {
            this.mCollectDao = collectDao;

        }

        @Override
        protected Void doInBackground(Collect... collects) {
            mCollectDao.insert(collects[0]);
            return null;
        }
    }

    class DeleteAsyncTask extends AsyncTask<Collect, Void, Void> {
        private final CollectDao mCollectDao;

        public DeleteAsyncTask(CollectDao collectDao) {
            this.mCollectDao = collectDao;

        }

        @Override
        protected Void doInBackground(Collect... collects) {
            mCollectDao.delete(collects[0]);
            return null;
        }
    }

    class UpdateAsyncTask extends AsyncTask<Collect, Void, Void> {
        private final CollectDao mCollectDao;

        public UpdateAsyncTask(CollectDao collectDao) {
            this.mCollectDao = collectDao;
        }

        @Override
        protected Void doInBackground(Collect... collects) {
            mCollectDao.update(collects[0]);
            return null;
        }
    }

}
