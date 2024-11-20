package com.muvi.shared.data.food_menu.apis

import com.muvi.data.food_menu.entities.FoodMenuItemEntity
import com.muvi.shared.data.core.ErrorResponse
import com.muvi.shared.data.core.MessageStatus
import com.muvi.shared.data.core.PaginationResponseEntity
import com.muvi.shared.data.core.ResponseApiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.muvi.shared.data.core.Result
import com.muvi.shared.data.util.PARAMETER
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class FoodMenuServiceImpl(private val httpClient: HttpClient) : FoodMenuService {
    override suspend fun getFoodMenu(
        page: Int,
        limit: Int
    ): Flow<Result<PaginationResponseEntity<FoodMenuItemEntity>, ErrorResponse>> {
        return flow {
            emit(Result.Loading)
            try {
                val responseBody = httpClient.get("") {
                    url {
//                       parameters.append(PARAMETER.QUERY, id)
                    }
                }.body<ResponseApiModel<PaginationResponseEntity<FoodMenuItemEntity>>>()
                when (responseBody.message) {
                    MessageStatus.Success -> {
                        emit(Result.Success(responseBody.data!!))
                    }

                    MessageStatus.Failure -> {
//                        emit(Result.Failure(ErrorResponse()))
                    }
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
//                emit(
//                    Result.Failure(
//                        Error(
//                            message = exception.message ?: "",
//                            throwable = exception
//                        )
//                    )
//                )
            }
        }
    }
}