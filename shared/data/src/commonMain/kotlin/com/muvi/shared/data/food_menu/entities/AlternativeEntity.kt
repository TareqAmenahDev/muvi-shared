package com.muvi.data.food_menu.entities

import kotlinx.serialization.Serializable

@Serializable
data class AlternativeEntity(
    val id: String,
    val name: String?,
    val imageUrl: String?,
    val price: Double?,
    val calories: Int?,
    val isVegan: Boolean?,
    val modifierGroups: List<ModifiersGroupEntity>?,
    val concessionTabsItems: List<ConcessionTabsItemsEntity>?,
)
