package com.muvi.data.food_menu.mappers

import com.muvi.data.food_menu.entities.UpsellingGroupEntity
import com.muvi.data.food_menu.entities.UpsellingItemEntity
import com.muvi.data.food_menu.entities.UpsellingItemSizeEntity
import com.muvi.domain.food_menu.models.UpsellingGroup
import com.muvi.domain.food_menu.models.UpsellingItem
import com.muvi.domain.food_menu.models.UpsellingItemSize

fun UpsellingItemSize.toEntity() =
    when (this) {
        UpsellingItemSize.Small -> UpsellingItemSizeEntity.Small
        UpsellingItemSize.Medium -> UpsellingItemSizeEntity.Medium
        UpsellingItemSize.Large -> UpsellingItemSizeEntity.Large
    }

fun UpsellingItemSizeEntity.toModel() =
    when (this) {
        UpsellingItemSizeEntity.Small -> UpsellingItemSize.Small
        UpsellingItemSizeEntity.Medium -> UpsellingItemSize.Medium
        UpsellingItemSizeEntity.Large -> UpsellingItemSize.Large
    }

fun UpsellingItem.toEntity() =
    UpsellingItemEntity(
        id = this.id,
        concessionItemId = this.concessionItemId,
        sort = this.sort,
        isDefault = this.isDefault,
        size = this.size.toEntity(),
        concessionItem = this.concessionItem.toEntity(),
    )

fun UpsellingItemEntity.toModel() =
    UpsellingItem(
        id = this.id,
        concessionItemId = this.concessionItemId,
        sort = this.sort,
        isDefault = this.isDefault,
        size = this.size.toModel(),
        concessionItem = com.muvi.shared.data.food_menu.mappers.toModel(),
    )

fun UpsellingGroup.toEntity() =
    UpsellingGroupEntity(
        parentConcessionItemId = this.parentConcessionItemId,
        name = this.name,
    )

fun UpsellingGroupEntity.toModel() =
    UpsellingGroup(
        parentConcessionItemId = this.parentConcessionItemId,
        name = this.name,
    )
