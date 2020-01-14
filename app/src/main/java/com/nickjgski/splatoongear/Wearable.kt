package com.nickjgski.splatoongear

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="wearable_table")
data class Wearable(@PrimaryKey @ColumnInfo(name="name") var name: String,
                    @ColumnInfo(name="type") var type: String,
                    @ColumnInfo(name="ability") var ability: String,
                    @ColumnInfo(name="rarity") var rarity: Int,
                    @ColumnInfo(name="image_path") var imagePath: String
)