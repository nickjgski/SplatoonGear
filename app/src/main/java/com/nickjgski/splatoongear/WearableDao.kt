package com.nickjgski.splatoongear

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WearableDao {

    @Query("SELECT * FROM wearable_table where type = 'Headgear' order BY name ASC")
    fun getAllHeadgear(): LiveData<List<Wearable>>

    @Query("SELECT * FROM wearable_table where type = 'Clothing' order BY name ASC")
    fun getAllClothing(): LiveData<List<Wearable>>

    @Query("SELECT * FROM wearable_table where type = 'Shoes' order BY name ASC")
    fun getAllShoes(): LiveData<List<Wearable>>

    @Insert
    fun insertAll(wearable: List<Wearable>)

}