package com.ph4n10m.budgetpro.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ph4n10m.budgetpro.dao.AppDatabase;
import com.ph4n10m.budgetpro.dao.SpendDao;
import com.ph4n10m.budgetpro.entity.ChartSpend;
import com.ph4n10m.budgetpro.entity.Spend;
import com.ph4n10m.budgetpro.entity.StatisticsCategorySpend;

import java.util.List;

public class SpendRepository {
    private final SpendDao mSpendDao;
    private final LiveData<List<Spend>> mAllSpend;

    public SpendRepository(Application application) {
        this.mSpendDao = AppDatabase.getDatabase(application).spendDao();
        mAllSpend = mSpendDao.findAll();
    }

    public LiveData<Float> sumSpend() {
        return mSpendDao.sumSpend();
    }

    public LiveData<List<StatisticsCategorySpend>> sumbyCategorySpend() {
        return mSpendDao.sumByCategorySpend();
    }

    public LiveData<List<ChartSpend>> getChartSpend() {
        return mSpendDao.getChartSpend();
    }

    public LiveData<List<Spend>> getAllSpend() {
        return mAllSpend;
    }

    public void insert(Spend Spend) {
        new InsertAsyncTask(mSpendDao).execute(Spend);
    }

    public void delete(Spend Spend) {
        new DeleteAsyncTask(mSpendDao).execute(Spend);
    }

    public void update(Spend Spend) {
        new UpdateAsyncTask(mSpendDao).execute(Spend);
    }

    class InsertAsyncTask extends AsyncTask<Spend, Void, Void> {
        private final SpendDao mSpendDao;

        public InsertAsyncTask(SpendDao SpendDao) {
            this.mSpendDao = SpendDao;

        }

        @Override
        protected Void doInBackground(Spend... Spends) {
            mSpendDao.insert(Spends[0]);
            return null;
        }
    }

    class DeleteAsyncTask extends AsyncTask<Spend, Void, Void> {
        private final SpendDao mSpendDao;

        public DeleteAsyncTask(SpendDao SpendDao) {
            this.mSpendDao = SpendDao;

        }

        @Override
        protected Void doInBackground(Spend... Spends) {
            mSpendDao.delete(Spends[0]);
            return null;
        }
    }

    class UpdateAsyncTask extends AsyncTask<Spend, Void, Void> {
        private final SpendDao mSpendDao;

        public UpdateAsyncTask(SpendDao SpendDao) {
            this.mSpendDao = SpendDao;
        }

        @Override
        protected Void doInBackground(Spend... Spends) {
            mSpendDao.update(Spends[0]);
            return null;
        }
    }

}
