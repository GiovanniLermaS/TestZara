package com.example.testzara.domain

import com.example.testzara.data.repository.FakeMainActivityRepository
import com.example.testzara.domain.character.GetCharacterUseCaseImpl
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class GetCharacterUseCaseTest {

    @Test
    fun `testExecute()`() {
        val useCase = GetCharacterUseCaseImpl(FakeMainActivityRepository())
        runBlocking {
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