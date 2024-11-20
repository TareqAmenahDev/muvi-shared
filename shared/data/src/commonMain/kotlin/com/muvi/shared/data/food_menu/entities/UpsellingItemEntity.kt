package com.muvi.data.food_menu.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpsellingItemEntity(
    val id: String,
    val concessionItemId: String,
    val sort: Int,
    val isDefault: Boolean,
    val size: UpsellingItemSizeEntity,
    val concessionItem: ConcessionItemEntity,
)

@Serializable
enum class UpsellingItemSizeEntity {
    @SerialName("SMALL")
    Small,

    @SerialName("MEDIUM")
    Medium,

    @SerialName("LARGE")
    Large,
}

@Serializable
data class UpsellingGroupEntity(
    val parentConcessionItemId: String,
    val name: String,
)
