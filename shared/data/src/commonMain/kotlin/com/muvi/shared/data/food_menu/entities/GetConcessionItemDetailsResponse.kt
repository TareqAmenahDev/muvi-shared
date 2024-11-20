package com.muvi.data.food_menu.entities

import kotlinx.serialization.Serializable

@Serializable
data class GetConcessionItemDetailsResponse(
    val concessionItem: ConcessionItemDetailsEntity,
)
