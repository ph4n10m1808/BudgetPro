package com.ph4n10m.budgetpro.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ph4n10m.budgetpro.entity.CategoryCollect;

import java.util.List;

@Dao
public interface CategoryCollectDao {
    @Query("SELECT * FROM CategoryCollect")
    LiveData<List<CategoryCollect>> findAll();

    @Insert
    void insert(CategoryCollect categoryCollect);

    @Update
    void update(CategoryCollect categoryCollect);

    @Delete
    void delete(CategoryCollect categoryCollect);

}
