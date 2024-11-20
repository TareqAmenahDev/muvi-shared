package com.muvi.data.food_menu.entities

import kotlinx.serialization.Serializable

@Serializable
data class PackageChildItemEntity(
    val id: String,
    val name: String?,
    val quantity: Int?,
    val concessionItemId: String?,
    val alternatives: List<AlternativeEntity>?,
)
