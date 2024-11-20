package com.muvi.shared.data.food_menu.mappers

import com.muvi.data.food_menu.entities.ConcessionItemDetailsEntity
import com.muvi.data.food_menu.entities.ConcessionItemEntity
import com.muvi.data.food_menu.entities.isOutOfStock
import com.muvi.data.food_menu.mappers.toModel
import com.muvi.domain.AppConstants.OUT_OF_STOCK_DEFAULT_VALUE
import com.muvi.domain.food_menu.models.ConcessionItem
import com.muvi.domain.food_menu.models.ConcessionItemDetails

fun ConcessionItemEntity.toModel() =
    ConcessionItem(
        id = this.id,
        name = this.name,
        description = this.description,
        imageUrl = this.imageUrl,
        price = this.price,
        calories = this.calories,
        isVegan = this.isVegan,
        isOutOfStock = this.concessionTabsItems?.firstOrNull()?.isOutOfStock() ?: OUT_OF_STOCK_DEFAULT_VALUE,
        alternatives = this.alternatives?.map { it.toModel() } ?: listOf(),
        upsellingGroup = this.upsellingGroup?.toModel(),
    )

fun ConcessionItemDetails.toEntity() =
    ConcessionItemDetailsEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        imageUrl = this.imageUrl,
        price = this.price,
        calories = this.calories,
        isVegan = this.isVegan,
        modifierGroups = this.modifierGroups.map { it.toEntity() },
        packageChildItems = this.packageChildItems.map { it.toEntity() },
        upsellingItems = this.upsellingItems.map { it.toEntity() },
        addOns = this.addOns.map { it.toEntity() },
        alternatives = this.alternatives.map { it.toEntity() },
        upsellingGroup = this.upsellingGroup?.toEntity(),
        concessionTabsItems = listOf(),
    )

fun ConcessionItem.toEntity() =
    ConcessionItemEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        imageUrl = this.imageUrl,
        price = this.price,
        calories = this.calories,
        isVegan = this.isVegan,
        alternatives = this.alternatives.map { it.toEntity() },
        upsellingGroup = this.upsellingGroup?.toEntity(),
        concessionTabsItems = listOf(),
    )

fun ConcessionItemDetailsEntity.toModel() =
    ConcessionItemDetails(
        id = this.id,
        name = this.name,
        description = this.description,
        imageUrl = this.imageUrl,
        price = this.price,
        calories = this.calories,
        isVegan = this.isVegan,
        isOutOfStock = this.concessionTabsItems?.firstOrNull()?.isOutOfStock() ?: OUT_OF_STOCK_DEFAULT_VALUE,
        modifierGroups = this.modifierGroups?.map { it.toModel() } ?: listOf(),
        packageChildItems = this.packageChildItems?.map { it.toModel() } ?: listOf(),
        upsellingItems = this.upsellingItems?.map { it.toModel() } ?: listOf(),
        addOns = this.addOns?.map { it.toModel() } ?: listOf(),
        alternatives = this.alternatives?.map { it.toModel() } ?: listOf(),
        upsellingGroup = this.upsellingGroup?.toModel(),
    )
