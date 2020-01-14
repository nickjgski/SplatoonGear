package com.nickjgski.splatoongear

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class GearViewModel(application: Application): AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val weaponRepo: WeaponRepository
    private val wearableRepo: WearableRepository
    var allWeapons: LiveData<List<Weapon>>
    var allHeadgear: LiveData<List<Wearable>>
    var allClothing: LiveData<List<Wearable>>
    var allShoes: LiveData<List<Wearable>>

    init {
        val weaponDao = WeaponDatabase.getInstance(application).weaponDao()
        weaponRepo = WeaponRepository(weaponDao)
        val wearableDao = WearableDatabase.getInstance(application).wearableDao()
        wearableRepo = WearableRepository(wearableDao)
        allWeapons = weaponRepo.allWeapons
        allHeadgear = wearableRepo.allHeadgear
        allClothing = wearableRepo.allClothing
        allShoes = wearableRepo.allShoes

    }

}