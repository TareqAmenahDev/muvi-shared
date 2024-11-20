package com.muvi.data.food_menu.entities

import kotlinx.serialization.Serializable

@Serializable
data class ModifiersGroupEntity(
    val id: String,
    val name: String?,
    val vistaId: String?,
    val maximumQuantity: Int?,
    val minimumQuantity: Int?,
    val modifiers: List<ModifierItemEntity>,
)

@Serializable
data class ModifierItemEntity(
    val id: String,
    val name: String?,
    val price: Double?,
    val concessionTabsItems: List<ConcessionTabsItemsEntity>?,
)
