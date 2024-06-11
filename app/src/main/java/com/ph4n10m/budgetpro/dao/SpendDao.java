package com.ph4n10m.budgetpro.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ph4n10m.budgetpro.entity.Spend;

import java.util.List;

@Dao
public interface SpendDao {
    @Query("SELECT * FROM Spend")
    LiveData<List<Spend>> findAll();

    @Insert
    void insert(Spend spend);

    @Update
    void update(Spend spend);

    @Delete
    void delete(Spend spend);

}
