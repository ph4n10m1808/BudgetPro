package com.ph4n10m.budgetpro.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.ph4n10m.budgetpro.entity.CategoryCollect;
import com.ph4n10m.budgetpro.entity.CategorySpend;
import com.ph4n10m.budgetpro.entity.Collect;
import com.ph4n10m.budgetpro.entity.Spend;

@Database(entities = {CategoryCollect.class, Collect.class, CategorySpend.class, Spend.class}, version = 7)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    private static final RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateData(INSTANCE).execute();
        }
    };

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "personal_db.db")
                            .fallbackToDestructiveMigration()
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract CategoryCollectDao categoryCollectDao();

    public abstract CollectDao collectDao();

    public abstract CategorySpendDao categorySpendDao();

    public abstract SpendDao spendDao();

    private static class PopulateData extends AsyncTask<Void, Void, Void> {
        private final CategoryCollectDao categoryCollectDao;
        private final CollectDao collectDao;
        private final CategorySpendDao categorySpendDao;
        private final SpendDao spendDao;

        PopulateData(AppDatabase db) {
            categoryCollectDao = db.categoryCollectDao();
            collectDao = db.collectDao();
            categorySpendDao = db.categorySpendDao();
            spendDao = db.spendDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String[] categorycollects = new String[]{"Lương", "Thưởng", "Khác"};
            for (String it : categorycollects) {
                CategoryCollect categorycollect = new CategoryCollect();
                categorycollect.name = it;
                categoryCollectDao.insert(categorycollect);
            }
            String[] categoryspends = new String[]{"Cố định", "Không cố định"};
            for (String it : categoryspends) {
                CategorySpend categorySpend = new CategorySpend();
                categorySpend.name = it;
                categorySpendDao.insert(categorySpend);
            }
            Collect collect = new Collect();
            collect.name = "Thu tháng 1";
            collect.money = 300000;
            collect.category_id = 1;
            collect.note = "1";
            collectDao.insert(collect);
            Spend spend = new Spend();
            spend.name = "Chi tháng 1";
            spend.money = 300000;
            spend.category_spend_id = 1;
            spend.note = "1";
            spendDao.insert(spend);

            Log.d("BudgetPro: ", "insert data");

            return null;
        }
    }
}
