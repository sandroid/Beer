package com.prepfully.beer.ui.main

import android.app.Application
import androidx.lifecycle.*
import com.prepfully.beer.network.Beer
import com.prepfully.beer.repository.BeerRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class BeerViewModel(repository: BeerRepository) : ViewModel() {

    private val _beerList = MutableLiveData<List<Beer>>()
    val beerList: LiveData<List<Beer>>
        get() = _beerList

    init {
        viewModelScope.launch {
            _beerList.value = repository.getBeers().value
        }
    }


    /**
     * Factory for constructing BeerViewModel with parameter
     */
    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val repository: BeerRepository = BeerRepository()
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BeerViewModel::class.java)) {
                return BeerViewModel(repository) as T
            }
            throw IllegalArgumentException("unable to create viewmodel")
        }
    }
}