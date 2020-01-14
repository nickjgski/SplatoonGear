package com.nickjgski.splatoongear

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WeaponDao {

    @Query("SELECT * from weapon_table order BY name ASC")
    fun getAllWeapons(): LiveData<List<Weapon>>

    @Insert
    fun insertAll(weapons: List<Weapon>)

}