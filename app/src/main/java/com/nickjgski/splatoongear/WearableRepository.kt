package com.nickjgski.splatoongear

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class WearableRepository(private val wearableDao: WearableDao) {

    val allHeadgear: LiveData<List<Wearable>> = wearableDao.getAllHeadgear()
    val allClothing: LiveData<List<Wearable>> = wearableDao.getAllClothing()
    val allShoes: LiveData<List<Wearable>> = wearableDao.getAllShoes()

}