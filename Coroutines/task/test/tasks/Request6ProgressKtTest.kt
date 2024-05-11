package tasks

import contributors.MockGithubService
import contributors.progressResults
import contributors.testRequestData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import tasks.loadContributorsProgress

class Request6ProgressKtTest {
    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun testProgress() = runTest {
        val startTime = currentTime
        var index = 0
        loadContributorsProgress(MockGithubService, testRequestData) { users, _ ->
            val expected = progressResults[index++]

            Assert.assertEquals("Expected intermediate result after virtual ${expected.timeFromStart} ms:",
                expected.timeFromStart, currentTime - startTime)

            Assert.assertEquals("Wrong intermediate result after ${currentTime - startTime}:", expected.users, users)
        }
    }
}
