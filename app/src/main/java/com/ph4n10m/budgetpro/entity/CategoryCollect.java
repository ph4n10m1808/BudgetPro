package com.ph4n10m.budgetpro.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CategoryCollect {
    @PrimaryKey(autoGenerate = true)
    public int category_id;
    @ColumnInfo(name = "name")
    public String name;
}
