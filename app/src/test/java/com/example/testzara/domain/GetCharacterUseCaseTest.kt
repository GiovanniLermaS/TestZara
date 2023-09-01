package com.example.testzara.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testzara.data.repository.FakeMainActivityRepository
import com.example.testzara.usecases.character.GetCharacterUseCaseImpl
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetCharacterUseCaseTest {

    private val dispatcher = TestCoroutineDispatcher()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        dispatcher.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }

    @Test
    fun `testExecute()`() {
        val useCase = GetCharacterUseCaseImpl(FakeMainActivityRepository())
        runBlockingTest {
            useCase.getCharacters(1, {
                assertEquals(1, it.results[0].id)
                assertNotEquals(5, it.results[10].id)
                assertNotNull(it.results)
                assertEquals("Earth (Replacement Dimension)", it.results[2].location.name)
            }, {
                assertNotEquals("Error", it)
            })
            useCase.getEpisode("", {
                assertEquals(null, it.name)
            }, {
                assertEquals("Error to process data", it)
            })
            useCase.getEpisode("https...", {
                assertEquals("Pilot", it.name)
            }, {
                assertEquals("Error to process data", it)
            })
        }
    }
}