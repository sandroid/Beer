package com.prepfully.beer

import com.prepfully.beer.repository.BeerRepository
import com.prepfully.beer.ui.main.BeerViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify

@ExperimentalCoroutinesApi
class BeerViewModelTestOrig {

    private val mockRepository : BeerRepository = mock()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @Test
    fun test() = coroutinesTestRule.testDispatcher.runBlockingTest {
        val subject = BeerViewModel(mockRepository)
        subject.beerList
        // Checks mockObject called the hashCode method that is expected from the coroutine created in sampleMethod
        verify(mockRepository).getBeers()
    }

}