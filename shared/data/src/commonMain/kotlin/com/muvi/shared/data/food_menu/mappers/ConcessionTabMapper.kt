package com.muvi.data.food_menu.mappers

import com.muvi.data.food_menu.entities.ConcessionTabEntity
import com.muvi.domain.food_menu.models.ConcessionTab

fun ConcessionTabEntity.toModel() =
    ConcessionTab(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
    )

fun ConcessionTab.toModel() =
    ConcessionTabEntity(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
    )
