package com.prepfully.beer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.prepfully.beer.network.Beer
import com.prepfully.beer.network.BeerApi

sealed class NetworkResult<out T : Any> {
    data class Success<out T : Any>(val data: List<Beer>) : NetworkResult<T>()
    data class Error(val exception: String) : NetworkResult<Nothing>()
}

class BeerRepository {
    suspend fun getBeers() : LiveData<NetworkResult<List<Beer>>> {
        val beerList = MutableLiveData<NetworkResult<List<Beer>>>()
        val response = BeerApi.retrofitService.getBeers()

       if (response.isSuccessful &&  response.errorBody() == null) {
            beerList.value = response.body()?.let { NetworkResult.Success(it) }
        } else {
            beerList.value = NetworkResult.Error("network call failed")
        }
        return beerList
    }
}