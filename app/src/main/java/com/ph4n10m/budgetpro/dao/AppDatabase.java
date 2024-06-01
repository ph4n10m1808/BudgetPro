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
import com.ph4n10m.budgetpro.entity.Collect;

@Database(entities = {CategoryCollect.class, Collect.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CategoryCollectDao categoryCollectDao();
    public abstract  CollectDao collectDao();

    public static AppDatabase INSTANCE;
    private static final RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateData(INSTANCE).execute();
        }
    };

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                AppDatabase.class,
                                "personal_db")
                        .fallbackToDestructiveMigration()
                        .addCallback(callback)
                        .build();
            }
        }
        return INSTANCE;
    }


    public static class PopulateData extends AsyncTask<Void, Void, Void> {
        private final CategoryCollectDao categoryCollectDao;
        private final CollectDao collectDao;

        public PopulateData(AppDatabase db) {
            categoryCollectDao = db.categoryCollectDao();
            collectDao = db.collectDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String[] categorycollects = new String[]{"Lương", "Thưởng", "Khác"};
            for (String it : categorycollects) {
                CategoryCollect categorycollect = new CategoryCollect();
                categorycollect.name = it;
                categoryCollectDao.insert(categorycollect);
            }
            Collect collect = new Collect();
            collect.name="Thu tháng 1";
            collect.money=3000;
            collect.category_id=1;
            collect.note="1";
            collectDao.insert(collect);
            Log.d("BudgetPro: ", "insert data");

            return null;
        }
    }

}
