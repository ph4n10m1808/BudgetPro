package com.ph4n10m.budgetpro.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ph4n10m.budgetpro.entity.ChartSpend;
import com.ph4n10m.budgetpro.entity.Spend;
import com.ph4n10m.budgetpro.entity.StatisticsCategorySpend;

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

    @Query("SELECT sum(money) FROM Spend")
    LiveData<Float> sumSpend();

    @Query("SELECT a.category_spend_id,b.name,sum(a.money) as total FROM Spend a INNER JOIN CategorySpend b on a.category_spend_id=b.category_spend_id " + "GROUP BY a.category_spend_id,b.name")
    LiveData<List<StatisticsCategorySpend>> sumByCategorySpend();

    @Query("SELECT name, money FROM Spend")
    LiveData<List<ChartSpend>> getChartSpend();
}
