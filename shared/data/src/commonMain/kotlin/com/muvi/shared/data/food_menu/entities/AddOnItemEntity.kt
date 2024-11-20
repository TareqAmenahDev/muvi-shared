package com.muvi.data.food_menu.entities

import kotlinx.serialization.Serializable

@Serializable
data class AddOnItemEntity(
    val id: String,
    val name: String,
    val concessionItem: ConcessionItemEntity?,
)
