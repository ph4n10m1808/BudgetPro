package com.ph4n10m.budgetpro.dao;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ph4n10m.budgetpro.entity.CategoryCollect;


@Database(entities = {CategoryCollect.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CategoryCollectDao categoryCollectDao();
    public static AppDatabase INSTANCE;
    public static AppDatabase getDatabase(final Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (AppDatabase.class)
            {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                AppDatabase.class, "personal_db")
                        .fallbackToDestructiveMigration()
                        .addCallback()
                        .build();
            }
        }
        return INSTANCE;
    }
    public static class PopulateData extends AsyncTask<Void, Void, Void>{
        private CategoryCollectDao categoryCollectDao;

        public PopulateData(AppDatabase db) {
            categoryCollectDao = db.categoryCollectDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String [] categorycollects = new String[]{"Luong","Thuong","Khac"};
            for(String it: categorycollects)
            {
                CategoryCollect categorycollect = new CategoryCollect();
                categorycollect.name=it;
                categoryCollectDao.insert(categorycollect);
            }
            return null;
        }
    }

}
