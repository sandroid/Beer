package com.prepfully.beer

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mindorks.example.coroutines.utils.TestCoroutineRule
import com.prepfully.beer.network.Beer
import com.prepfully.beer.network.BeerApi
import com.prepfully.beer.repository.BeerRepository
import com.prepfully.beer.ui.main.BeerViewModel
import io.mockk.mockkObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class BeerViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var mockBeerRepository: BeerRepository

    @Mock
    private lateinit var mockBeerObserver: Observer<List<Beer>>

    @Before
    fun setUp() {
        // do something if required
    }

    @Test
    fun getBeersShouldReturnList() {

        mockkObject(BeerApi)

        testCoroutineRule.runBlockingTest {
            doReturn(emptyList<Beer>())
                .`when`(mockkObject(BeerApi.retrofitService.getBeers()))
            val viewModel = BeerViewModel(mockBeerRepository)
            viewModel.beerList.observeForever(mockBeerObserver)
            verify(BeerApi.retrofitService.getBeers())
            verify(mockBeerObserver).onChanged(emptyList())
            viewModel.beerList.removeObserver(mockBeerObserver)
        }
    }


    @After
    fun tearDown() {
        // do something if required
    }

}