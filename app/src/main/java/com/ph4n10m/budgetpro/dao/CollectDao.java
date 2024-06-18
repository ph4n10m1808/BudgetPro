package com.ph4n10m.budgetpro.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ph4n10m.budgetpro.entity.ChartCollect;
import com.ph4n10m.budgetpro.entity.Collect;
import com.ph4n10m.budgetpro.entity.StatisticsCategoryCollect;

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

    @Query("SELECT sum(money) FROM Collect")
    LiveData<Float> sumCollect();

    @Query("SELECT a.category_id,b.name,sum(a.money) as total FROM Collect a INNER JOIN CategoryCollect b on a.category_id=b.category_id " + "GROUP BY a.category_id,b.name")
    LiveData<List<StatisticsCategoryCollect>> sumByCategoryCollect();

    @Query("SELECT name, money FROM Collect")
    LiveData<List<ChartCollect>> getChartCollect();

}
