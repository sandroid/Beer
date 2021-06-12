package com.prepfully.beer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.prepfully.beer.network.Beer
import com.prepfully.beer.network.BeerApi
import timber.log.Timber

sealed class NetworkResult<out T : Any> {
    data class Success<out T : Any>(val data: List<Beer>) : NetworkResult<T>()
    data class Error(val exception: String) : NetworkResult<Nothing>()
}

/**
 * Repository to fetch beer data from network
 * TODO:  Add Room database to use as an offline cache
 * TODO:  Display data that you fetch from the database to decrease app-loading time.
 */
class BeerRepository : IBeerRepository {
    suspend fun getBeers() : LiveData<NetworkResult<List<Beer>>> {
        val beerList = MutableLiveData<NetworkResult<List<Beer>>>()
        try {
            val response = BeerApi.retrofitService.getBeers()

            if (response.isSuccessful &&  response.errorBody() == null) {
                beerList.value = response.body()?.let { NetworkResult.Success(it) }
            } else {
                beerList.value = NetworkResult.Error("network call failed")
            }
        } catch (e: Exception) {
            Timber.d("Network exception ${e.message}")
            beerList.value = NetworkResult.Error("Network exception ${e.message}")
        }
        return beerList
    }
}