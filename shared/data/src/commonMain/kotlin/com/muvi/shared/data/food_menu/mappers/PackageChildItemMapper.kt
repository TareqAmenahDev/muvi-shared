package com.muvi.data.food_menu.mappers

import com.muvi.data.food_menu.entities.PackageChildItemEntity
import com.muvi.domain.food_menu.models.PackageChildItem

fun PackageChildItemEntity.toModel() =
    PackageChildItem(
        id = this.id,
        name = this.name,
        quantity = this.quantity,
        concessionItemId = this.concessionItemId,
        alternatives = this.alternatives?.map { it.toModel() } ?: emptyList(),
    )

fun PackageChildItem.toEntity() =
    PackageChildItemEntity(
        id = this.id,
        name = this.name,
        quantity = this.quantity,
        concessionItemId = this.concessionItemId,
        alternatives = this.alternatives.map { it.toEntity() },
    )
