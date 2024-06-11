package com.ph4n10m.budgetpro.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CategorySpend {
    @PrimaryKey(autoGenerate = true)
    public int catrgory_spend_id;
    @ColumnInfo(name="name")
    public String name;
}
