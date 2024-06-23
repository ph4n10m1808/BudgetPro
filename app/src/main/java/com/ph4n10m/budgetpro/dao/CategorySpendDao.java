package com.ph4n10m.budgetpro.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ph4n10m.budgetpro.entity.CategorySpend;

import java.util.List;

@Dao
public interface CategorySpendDao {
    @Query("SELECT * FROM CategorySpend")
    LiveData<List<CategorySpend>> findAll();

    @Insert
    void insert(CategorySpend categorySpend);

    @Update
    void update(CategorySpend categorySpend);

    @Delete
    void delete(CategorySpend categorySpend);

    @Query("SELECT name FROM CategorySpend WHERE category_spend_id = :id")
    LiveData<String> getName(int id);
}
