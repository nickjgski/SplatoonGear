package com.nickjgski.splatoongear

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "weapon_table")
data class Weapon(@PrimaryKey @ColumnInfo(name="name") var name: String,
                  @ColumnInfo(name="sub") var sub: String,
                  @ColumnInfo(name="special") var special: String,
                  @ColumnInfo(name="type") var type: String,
                  @ColumnInfo(name="image_path") var imagePath: String
)