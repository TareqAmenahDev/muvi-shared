package com.muvi.muvishared.concessions_cart

import com.muvi.muvishared.deepEquals
import com.muvi.muvishared.models.AlternativeItem
import com.muvi.muvishared.models.ConcessionItemDetails
import com.muvi.muvishared.models.ModifierItem

typealias ModifierGroupId = String

data class ModifierGroupsCustomization(
    val map: Map<ModifierGroupId, ModifiersCustomization> = mapOf(),
) {
    fun selectAndClone(
        modifierGroupId: ModifierGroupId,
        index: Index,
        modifier: ModifierItem,
    ): ModifierGroupsCustomization {
        val mutableMap: MutableMap<ModifierGroupId, ModifiersCustomization> = map.toMutableMap()

        when (val modifierGroupCustomization = mutableMap[modifierGroupId]) {
            null ->
                mutableMap[modifierGroupId] =
                    ModifiersCustomization(
                        modifierByIndexMap = mapOf(index to modifier),
                    )

            else ->
                mutableMap[modifierGroupId] =
                    modifierGroupCustomization.selectModifierAndClone(
                        index = index,
                        modifier = modifier,
                    )
        }

        return ModifierGroupsCustomization(mutableMap.toMap())
    }

    // ModifierGroupsCustomization will be valid to use with concession
    // item when it has modifier for each modifier group item with the same quantity
    fun isValidToUseWithConcessionItem(concessionItemDetails: ConcessionItemDetails): Boolean {
        concessionItemDetails.modifierGroups.forEach { modifiersGroup ->
            val customization = map[modifiersGroup.id]

            if (customization == null || customization.modifierByIndexMap.size != modifiersGroup.maximumQuantity) {
                return false
            }
        }
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        if (this::class != other::class) return false

        other as ModifierGroupsCustomization

        return map.deepEquals(other.map)
    }

    override fun hashCode(): Int {
        return map.hashCode()
    }
}

data class AlternativeCustomization(
    val alternativeItem: AlternativeItem,
    // Will be used in mix modifiers cases
    val modifiersCustomization: ModifiersCustomization?,
) {
    fun selectModifierAndClone(
        index: Index,
        modifier: ModifierItem,
    ): AlternativeCustomization {
        return AlternativeCustomization(
            alternativeItem = alternativeItem,
            modifiersCustomization =
            when (modifiersCustomization) {
                null -> {
                    ModifiersCustomization(
                        modifierByIndexMap = mapOf(index to modifier),
                    )
                }

                else -> {
                    modifiersCustomization.selectModifierAndClone(
                        index = index,
                        modifier = modifier,
                    )
                }
            },
        )
    }

    fun isValidToAddToCart(): Boolean {
        if (alternativeItem.modifierGroups == null) {
            return true
        }

        alternativeItem.modifierGroups.maximumQuantity?.let {
            if (it <= 1) {
                return true
            }
        }

        return modifiersCustomization?.modifierByIndexMap?.size == alternativeItem.modifierGroups.maximumQuantity
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        if (this::class != other::class) return false

        other as AlternativeCustomization

        return alternativeItem.id == other.alternativeItem.id &&
                modifiersCustomization == other.modifiersCustomization
    }

    override fun hashCode(): Int {
        var result = alternativeItem.hashCode()
        result = 31 * result + (modifiersCustomization?.hashCode() ?: 0)
        return result
    }
}

data class ModifiersCustomization(
    val modifierByIndexMap: Map<Index, ModifierItem>,
) {
    fun selectModifierAndClone(
        index: Index,
        modifier: ModifierItem,
    ): ModifiersCustomization {
        val mutableMap = modifierByIndexMap.toMutableMap()
        mutableMap[index] = modifier

        return ModifiersCustomization(
            modifierByIndexMap = mutableMap.toMap(),
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        if (this::class != other::class) return false

        other as ModifiersCustomization

        return modifierByIndexMap.deepEquals(other.modifierByIndexMap)
    }

    override fun hashCode(): Int {
        return modifierByIndexMap.hashCode()
    }
}

fun ModifierGroupsCustomization.getModifierPrices(): Double {
    var modifiersTotalPrice = 0.0
    map.values.forEach { modifiersCustomization ->
        modifiersCustomization.modifierByIndexMap.values.forEach { modifierItem ->
            modifiersTotalPrice += (modifierItem.price ?: 0.0)
        }
    }

    return modifiersTotalPrice
}
