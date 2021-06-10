package com.prepfully.beer.ui.main

import androidx.lifecycle.*
import com.prepfully.beer.network.Beer
import com.prepfully.beer.repository.BeerRepository
import com.prepfully.beer.repository.NetworkResult
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class BeerViewModel(repository: BeerRepository) : ViewModel() {

    private val _beerList = MutableLiveData<List<Beer>>()
    val beerList: LiveData<List<Beer>>
        get() = _beerList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    init {
        viewModelScope.launch {
            when(val response = repository.getBeers().value) {
                is NetworkResult.Error -> _errorMessage.value = response.exception
                is NetworkResult.Success -> {
                    _beerList.value = response.data
                }
            }
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