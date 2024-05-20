package com.ph4n10m.budgetpro.dao;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.ph4n10m.budgetpro.entity.CategoryCollect;

@Database(entities = {CategoryCollect.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CategoryCollectDao categoryCollectDao();

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

        public PopulateData(AppDatabase db) {
            categoryCollectDao = db.categoryCollectDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String[] categorycollects = new String[]{"Lương", "Thưởng", "Khác"};
            for (String it : categorycollects) {
                CategoryCollect categorycollect = new CategoryCollect();
                categorycollect.name = it;
                categoryCollectDao.insert(categorycollect);
            }
            return null;
        }
    }

}
