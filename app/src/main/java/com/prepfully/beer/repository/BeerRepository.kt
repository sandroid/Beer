package com.prepfully.beer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.prepfully.beer.network.Beer
import com.prepfully.beer.network.BeerApi
import kotlinx.coroutines.withContext

class BeerRepository {

    suspend fun getBeers() : LiveData<List<Beer>> {
        val beerList = MutableLiveData<List<Beer>>()
        beerList.value = BeerApi.retrofitService.getBeers()
        return beerList
    }

}