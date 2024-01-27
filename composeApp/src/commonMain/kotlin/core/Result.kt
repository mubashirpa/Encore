package core

// sealed interface Result<out R> {
//    class Success<out R>(val value: R) : Result<R>
//
//    data object Loading : Result<Nothing>
//
//    class Error(val throwable: Throwable) : Result<Nothing>
// }

sealed class Result<T>(val data: T? = null, val message: String? = null) {
    class Empty<T> : Result<T>()

    class Error<T>(message: String, data: T? = null) : Result<T>(data, message)

    class Loading<T>(data: T? = null) : Result<T>(data)

    class Success<T>(data: T?) : Result<T>(data)
}
