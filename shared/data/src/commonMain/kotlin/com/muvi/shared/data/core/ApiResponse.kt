//package com.muvi.shared.data.core
//
//import kotlinx.serialization.ExperimentalSerializationApi
//import kotlinx.serialization.decodeFromString
//
//@Suppress("unused")
//sealed class ApiResponse<T> {
//    abstract fun toResource(): Resource<T>
//
//    /**
//     * API Success response class from retrofit.
//     *
//     * [data] is optional. (There are responses without data)
//     */
//    class Success<T>(response: Response<T>) : ApiResponse<T>() {
//        private val statusCode = response.code()
//        val data: T? = response.body()
//
//        override fun toString() = "[ApiResponse.Success]: $data"
//
//        override fun toResource(): Resource<T> {
//            return Resource.Success(statusCode, data)
//        }
//    }
//
//    /**
//     * API Failure response class.
//     *
//     * ## Throw Exception case.
//     * Gets called when an unexpected exception occurs while creating the request or processing the response.
//     *
//     * ## API Network format error case.
//     * API communication conventions do not match or applications need to handle errors.
//     */
//    sealed class Failure<T> {
//        @ExperimentalSerializationApi
//        class Error<T>(response: Response<out T>) : ApiResponse<T>() {
//            private val statusCode: Int = response.code()
//            private val responseBody: ResponseBody? = response.errorBody()
//            private val json = BaseJsonBuilder()
//
//            private val errorResponse = json.decodeFromString<ErrorResponse>(responseBody?.string() ?: "")
//
//            override fun toString(): String = "[ApiResponse.Failure.Error $statusCode]: $responseBody"
//
//            override fun toResource(): Resource<T> {
//                return Resource.Error(errorResponse.statusCode, errorResponse.code, errorResponse.message, null, errorResponse.data)
//            }
//        }
//
//        class Exception<T>(exception: Throwable) : ApiResponse<T>() {
//            private val message: String? = exception.localizedMessage
//
//            private val errorResponse = Resource.Error<T>(UNKNOWN_ERROR_CODE, UNKNOWN_ERROR_CODE, message ?: "")
//
//            override fun toString(): String = "[ApiResponse.Failure.Exception]: $message"
//
//            override fun toResource(): Resource<T> {
//                return errorResponse
//            }
//        }
//    }
//
//    companion object {
//        /**
//         * ApiResponse Factory
//         *
//         * [Failure] factory function. Only receives [Throwable] arguments.
//         */
//        fun <T> error(ex: Throwable): ApiResponse<T> {
//            Timber.e(ex)
//            return Failure.Exception(ex)
//        }
//
//        /**
//         * ApiResponse Factory
//         *
//         * [f] Create ApiResponse from [retrofit2.Response] returning from the block.
//         * If [retrofit2.Response] has no errors, it will create [ApiResponse.Success]
//         * If [retrofit2.Response] has errors, it will create [ApiResponse.Failure.Error]
//         */
//        @OptIn(ExperimentalSerializationApi::class)
//        fun <T> of(f: () -> Response<T>): ApiResponse<T> =
//            try {
//                val response = f()
//                if (response.isSuccessful) {
//                    Timber.d("Success Response $response")
//                    Success(response)
//                } else {
//                    Timber.e("Failure Error Response $response")
//                    Failure.Error(response)
//                }
//            } catch (ex: Exception) {
//                Timber.e("Failure Exception Response ${ex.message}")
//                Failure.Exception(ex)
//            }
//    }
//}
