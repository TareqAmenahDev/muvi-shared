package com.muvi.shared.data.food_menu.apis

import com.muvi.data.food_menu.entities.ConcessionItemEntity
import com.muvi.data.food_menu.entities.FoodMenuItemEntity
import com.muvi.data.food_menu.entities.GetConcessionItemDetailsResponse
import com.muvi.data.food_menu.responses.GetConcessionTabsResponse
import com.muvi.data.food_menu.responses.GetSuggestionsResponse
import com.muvi.shared.data.core.ErrorResponse
import com.muvi.shared.data.core.PaginationResponseEntity
import com.muvi.shared.data.core.Result
import kotlinx.coroutines.flow.Flow

interface FoodMenuService {
    suspend fun getFoodMenu(
       page: Int,
        limit: Int,
    ): Flow<Result<PaginationResponseEntity<FoodMenuItemEntity>, ErrorResponse>>

}

