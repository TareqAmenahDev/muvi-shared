package com.muvi.data.food_menu.entities

import kotlinx.serialization.Serializable

@Serializable
data class FoodMenuItemEntity(
    val id: String,
    val thumbnailUrl: String?,
    val fileUrl: String,
)
