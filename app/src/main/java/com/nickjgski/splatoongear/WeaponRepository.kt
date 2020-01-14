package com.nickjgski.splatoongear

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class WeaponRepository(private val weaponDao: WeaponDao) {

    val allWeapons: LiveData<List<Weapon>> = weaponDao.getAllWeapons()

}