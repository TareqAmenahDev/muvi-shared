package com.muvi.data.food_menu.responses

import com.muvi.data.food_menu.entities.ConcessionItemEntity
import kotlinx.serialization.Serializable

@Serializable
data class GetSuggestionsResponse(
    val data: List<ConcessionItemEntity>,
)
