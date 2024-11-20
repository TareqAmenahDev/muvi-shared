package com.muvi.data.food_menu.mappers

import com.muvi.data.food_menu.entities.ModifierItemEntity
import com.muvi.data.food_menu.entities.ModifiersGroupEntity
import com.muvi.data.food_menu.entities.isOutOfStock
import com.muvi.domain.AppConstants.OUT_OF_STOCK_DEFAULT_VALUE
import com.muvi.domain.food_menu.models.ModifierItem
import com.muvi.domain.food_menu.models.ModifiersGroup

fun ModifiersGroupEntity.toModel() =
    ModifiersGroup(
        id = this.id,
        name = this.name,
        vistaId = this.vistaId,
        maximumQuantity = this.maximumQuantity,
        minimumQuantity = this.maximumQuantity,
        modifiers = this.modifiers.map { it.toModel() },
    )

fun ModifiersGroup.toEntity() =
    ModifiersGroupEntity(
        id = this.id,
        name = this.name,
        vistaId = this.vistaId,
        maximumQuantity = this.maximumQuantity,
        minimumQuantity = this.maximumQuantity,
        modifiers = this.modifiers.map { it.toEntity() },
    )

fun ModifierItemEntity.toModel() =
    ModifierItem(
        id = this.id,
        name = this.name,
        price = this.price,
        isOutOfStock = this.concessionTabsItems?.firstOrNull()?.isOutOfStock() ?: OUT_OF_STOCK_DEFAULT_VALUE,
    )

fun ModifierItem.toEntity() =
    ModifierItemEntity(
        id = this.id,
        name = this.name,
        price = this.price,
        concessionTabsItems = null,
    )
