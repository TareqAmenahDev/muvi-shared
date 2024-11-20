package com.muvi.shared.data.food_menu.datasources

import com.muvi.data.food_menu.entities.FoodMenuItemEntity
import com.muvi.shared.data.core.ErrorResponse
import com.muvi.shared.data.core.PaginationResponseEntity
import com.muvi.shared.data.food_menu.apis.FoodMenuService
import kotlinx.coroutines.flow.Flow
import com.muvi.shared.data.core.Result


interface FoodMenuRemoteDataSource {
    suspend fun getFoodMenu(
        page: Int,
        limit: Int,
    ): Flow<Result<PaginationResponseEntity<FoodMenuItemEntity>, ErrorResponse>>

}

class FoodMenuRemoteDataSourceImpl(private val foodMenuService: FoodMenuService) :
    FoodMenuRemoteDataSource {
    override suspend fun getFoodMenu(
        page: Int,
        limit: Int,
    ): Flow<Result<PaginationResponseEntity<FoodMenuItemEntity>,ErrorResponse>> {
        return foodMenuService.getFoodMenu(page, limit)
    }

}
