package com.example.common

import app.cash.turbine.test
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ResourceTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testResourceSteps() = runTest {
        flow {
            emit("test")
            throw Exception("Test Done")
        }
            .asResult()
            .test {
                assertEquals(Resource.Loading<String>(null).data, awaitItem().data)
                assertEquals(Resource.Success("test").data, awaitItem().data)
                assertEquals(
                    Resource.Error(data = null, message = "Test Done").message,
                    awaitItem().message
                )
                awaitComplete()
            }
    }
}