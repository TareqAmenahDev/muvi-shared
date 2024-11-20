package com.muvi.data.food_menu.repositories

import com.muvi.domain.core.models.PaginationResult
import com.muvi.domain.core.models.Resource
import com.muvi.domain.food_menu.models.ConcessionItem
import com.muvi.domain.food_menu.models.ConcessionItemDetails
import com.muvi.domain.food_menu.models.ConcessionTab
import com.muvi.domain.food_menu.models.FoodMenuItem
import com.muvi.domain.food_menu.models.mock.FBMockData
import com.muvi.domain.food_menu.repositories.FoodMenuRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockFoodMenuRepositoryImpl : FoodMenuRepository {
    private val fbMockData = FBMockData()

    override fun getFoodMenu(
        page: Int,
        limit: Int,
    ): Flow<Resource<PaginationResult<FoodMenuItem>>> {
        return flow {
            // TODO("Not yet implemented")
        }
    }

    override fun getConcessionTabs(): Flow<Resource<List<ConcessionTab>>> {
        return flow {
            emit(Resource.Loading())
            delay(1000)
            emit(Resource.Success(statusCode = 200, data = fbMockData.mockConcessionTabs))
        }
    }

    override fun getConcessionItems(
        concessionTabId: String,
        page: Int,
    ): Flow<Resource<PaginationResult<ConcessionItem>>> {
        return flow {
            emit(Resource.Loading())
            delay(1000)
            emit(
                Resource.Success(
                    statusCode = 200,
                    data =
                        PaginationResult(
                            1,
                            1,
                            fbMockData.mockDetailsConcessionItemsList.size,
                            fbMockData.mockDetailsConcessionItemsList.size,
                            fbMockData.mockDetailsConcessionItemsList,
                        ),
                ),
            )
        }
    }

    override fun getConcessionItemDetails(concessionItemId: String): Flow<Resource<ConcessionItemDetails>> {
        return flow {
            // TODO("Not yet implemented")
        }
    }

    override fun getSuggestedItems(
        concessionItems: List<String>,
    ): Flow<Resource<List<ConcessionItem>>> {
        return flow {
            // TODO("Not yet implemented")
        }
    }
}
