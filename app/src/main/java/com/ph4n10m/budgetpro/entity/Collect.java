package com.ph4n10m.budgetpro.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Collect {
    @PrimaryKey(autoGenerate = true)
    public int collect_id;
    @ColumnInfo(name="category_id")
    public int category_id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name="money")
    public float money;
    @ColumnInfo(name="note")
    public String note;
}
