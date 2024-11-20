package com.muvi.data.food_menu.mappers

import com.muvi.data.food_menu.entities.FoodMenuItemEntity
import com.muvi.domain.food_menu.models.FoodMenuItem

fun FoodMenuItemEntity.toModel() =
    FoodMenuItem(
        id = id,
        thumbnailUrl = thumbnailUrl,
        fileUrl = fileUrl,
    )

fun FoodMenuItem.toEntity() =
    FoodMenuItemEntity(
        id = id,
        thumbnailUrl = thumbnailUrl,
        fileUrl = fileUrl,
    )
