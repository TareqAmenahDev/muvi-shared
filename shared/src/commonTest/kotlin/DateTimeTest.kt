import kotlinx.datetime.Clock
import kotlin.test.Test

class DateTimeTest {

    @Test
    fun shouldFindMatches() {
        for (i in 1..1000) {
            val timestamp = Clock.System.now().epochSeconds
            println(timestamp)
        }
    }
}
