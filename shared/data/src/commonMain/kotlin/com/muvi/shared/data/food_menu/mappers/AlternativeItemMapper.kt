package com.muvi.data.food_menu.mappers

import com.muvi.data.food_menu.entities.AlternativeEntity
import com.muvi.data.food_menu.entities.isOutOfStock
//import com.muvi.domain.AppConstants.OUT_OF_STOCK_DEFAULT_VALUE
//import com.muvi.domain.food_menu.models.AlternativeItem

//fun AlternativeEntity.toModel() =
//    AlternativeItem(
//        id = this.id,
//        name = this.name,
//        price = this.price,
//        imageUrl = imageUrl,
//        isVegan = isVegan,
//        calories = calories,
//        modifierGroups = this.modifierGroups?.firstOrNull()?.toModel(),
//        isOutOfStock = this.concessionTabsItems?.firstOrNull()?.isOutOfStock() ?: OUT_OF_STOCK_DEFAULT_VALUE,
//    )
//
//fun AlternativeItem.toEntity() =
//    AlternativeEntity(
//        id = this.id,
//        name = this.name,
//        price = this.price,
//        imageUrl = imageUrl,
//        isVegan = isVegan,
//        calories = calories,
//        modifierGroups = modifierGroups?.let { listOf(it.toEntity()) },
//        concessionTabsItems = null,
//    )
