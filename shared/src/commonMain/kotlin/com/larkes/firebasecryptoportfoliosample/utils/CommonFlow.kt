import io.ktor.utils.io.core.Closeable
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T> StateFlow<T>.asCommonFlow(): CommonFlow<T> = CommonFlow(this)
class CommonFlow<T>(private val origin: Flow<T>) : Flow<T> by origin {
    private val scope = MainScope()

    fun watch(block: (T) -> Unit): Closeable {
        val job = onEach(block).launchIn(scope)
        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }
}