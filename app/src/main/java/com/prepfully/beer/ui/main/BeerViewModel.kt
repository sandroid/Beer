package com.prepfully.beer.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prepfully.beer.network.Beer
import com.prepfully.beer.network.BeerApi
import com.prepfully.beer.repository.BeerRepository
import kotlinx.coroutines.launch

class BeerViewModel : ViewModel() {

    private val _beerList = MutableLiveData<List<Beer>>()
    val beerList: LiveData<List<Beer>>
    get() = _beerList

    init {
        viewModelScope.launch {
            val repository = BeerRepository()
            _beerList.value = repository.getBeers().value
        }
    }
}