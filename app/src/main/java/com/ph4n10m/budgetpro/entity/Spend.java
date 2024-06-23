package com.ph4n10m.budgetpro.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        foreignKeys = @ForeignKey(
                entity = CategorySpend.class,
                parentColumns = "category_spend_id",
                childColumns = "category_spend_id",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
        )
)
public class Spend {
    @PrimaryKey(autoGenerate = true)
    public int spend_id;
    @ColumnInfo(name = "category_spend_id")
    public int category_spend_id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "money")
    public float money;
    @ColumnInfo(name = "note")
    public String note;

}
