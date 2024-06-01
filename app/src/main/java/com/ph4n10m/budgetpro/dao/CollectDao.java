package com.ph4n10m.budgetpro.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

 import com.ph4n10m.budgetpro.entity.Collect;

import java.util.List;

@Dao
public interface CollectDao {
    @Query("SELECT * FROM Collect")
    LiveData<List<Collect>> findAll();

    @Insert
    void insert(Collect collect);

    @Update
    void update(Collect collect);

    @Delete
    void delete(Collect collect);

}
