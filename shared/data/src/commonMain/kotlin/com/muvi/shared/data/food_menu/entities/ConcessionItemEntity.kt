package com.muvi.data.food_menu.entities

import kotlinx.serialization.Serializable

@Serializable
data class ConcessionItemEntity(
    val id: String,
    val name: String?,
    val description: String?,
    val imageUrl: String?,
    val price: Double?,
    val calories: Int?,
    val isVegan: Boolean?,
    val alternatives: List<AlternativeEntity>?,
    val upsellingGroup: UpsellingGroupEntity?,
    val concessionTabsItems: List<ConcessionTabsItemsEntity>?,
)

@Serializable
data class ConcessionItemDetailsEntity(
    val id: String,
    val name: String?,
    val description: String?,
    val imageUrl: String?,
    val price: Double?,
    val calories: Int?,
    val isVegan: Boolean?,
    val modifierGroups: List<ModifiersGroupEntity>?,
    val alternatives: List<AlternativeEntity>?,
    val packageChildItems: List<PackageChildItemEntity>?,
    val upsellingItems: List<UpsellingItemEntity>?,
    val addOns: List<AddOnItemEntity>?,
    val upsellingGroup: UpsellingGroupEntity?,
    val concessionTabsItems: List<ConcessionTabsItemsEntity>?,
)
