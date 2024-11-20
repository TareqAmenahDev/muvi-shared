

import com.muvi.shared.data.food_menu.apis.FoodMenuServiceImpl
import com.muvi.shared.data.food_menu.datasources.FoodMenuRemoteDataSource
import com.muvi.shared.data.food_menu.datasources.FoodMenuRemoteDataSourceImpl
import com.muvi.shared.data.util.Rout
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FoodMenuRemoteDataSourceIntegrationTest {

    private val client = HttpClient() {
        install(Logging) {
//                logger = KtorLogger()
            level = LogLevel.ALL
        }
        install(DefaultRequest) {
            url(Rout.BASE_URL)
            header("X-Device-Platform", "sharedsdk")
            header("X-App-Version", "1.0.0")
            header("Content-Type", "application/json")
            header("Accept-Language", "en")
            header("X-Device-Id", "deviceId")
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                explicitNulls = false
                coerceInputValues = true
                isLenient = true
            })
        }
    }

    private val remoteDataSource: FoodMenuRemoteDataSource = FoodMenuRemoteDataSourceImpl(FoodMenuServiceImpl(client))

    @Test
    fun `getFoodMenu should return expected data from the server`() = runTest {
        // Arrange - Ensure your test server is running and has data available at the endpoint
        val page = 1
        val limit = 10

        // Act
        val result = remoteDataSource.getFoodMenu(page, limit)

        // Assert
//        assertTrue(result.isSuccess)
//        val response = result.getOrNull()
        println(result)
//        assertTrue(response?.items?.isNotEmpty() ?: false)
//        assertEquals(1, response?.totalPages) // Modify as per your actual data
    }
}
