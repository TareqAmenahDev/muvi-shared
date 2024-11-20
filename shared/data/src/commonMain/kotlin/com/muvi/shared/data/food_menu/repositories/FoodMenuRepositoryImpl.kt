package com.muvi.shared.data.food_menu.repositories

import com.muvi.data.food_menu.entities.FoodMenuItemEntity
import com.muvi.shared.data.core.ErrorResponse
import com.muvi.shared.data.core.PaginationResponseEntity
import com.muvi.shared.data.core.Result
import com.muvi.shared.data.food_menu.datasources.FoodMenuRemoteDataSource
import kotlinx.coroutines.flow.Flow

class FoodMenuRepositoryImpl(private val remoteDataSource: FoodMenuRemoteDataSource) {
    suspend fun getFoodMenu(
        page: Int,
        limit: Int,
    ): Flow<Result<PaginationResponseEntity<FoodMenuItemEntity>, ErrorResponse>> {
        return remoteDataSource.getFoodMenu(page, limit)
    }

}
