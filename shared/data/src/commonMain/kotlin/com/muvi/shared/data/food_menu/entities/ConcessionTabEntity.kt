package com.muvi.data.food_menu.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConcessionTabEntity(
    val id: String,
    val name: String,
    val imageUrl: String?,
)

@Serializable
data class ConcessionTabsItemsEntity(
    val id: String,
    val actualDisplayType: ActualDisplayTypeEntity,
)

@Serializable
enum class ActualDisplayTypeEntity {
    @SerialName("APPARENT")
    Apparent,

    @SerialName("DISABLE")
    Disable,
}

fun ConcessionTabsItemsEntity.isOutOfStock(): Boolean {
    return actualDisplayType == ActualDisplayTypeEntity.Disable
}
