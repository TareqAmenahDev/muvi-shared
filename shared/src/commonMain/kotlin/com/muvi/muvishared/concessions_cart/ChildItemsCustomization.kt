package com.muvi.muvishared.concessions_cart

import com.muvi.muvishared.deepEquals
import com.muvi.muvishared.models.ConcessionItemDetails

typealias PackageChildItemId = String
typealias Index = Int

data class ChildItemsCustomization(
    val map: Map<PackageChildItemId, Map<Index, AlternativeCustomization>> = mapOf(),
) {
    fun selectAndClone(
        itemId: String,
        index: Int,
        alternativeCustomization: AlternativeCustomization,
    ): ChildItemsCustomization {
        val mutableMap: MutableMap<PackageChildItemId, MutableMap<Int, AlternativeCustomization>> = mutableMapOf()
        for ((key, value) in map) {
            mutableMap[key] = value.toMutableMap()
        }

        if (mutableMap[itemId] == null) {
            mutableMap[itemId] = mutableMapOf(index to alternativeCustomization)
        } else {
            mutableMap[itemId]?.set(index, alternativeCustomization)
        }

        val updatedMap = mutableMapOf<PackageChildItemId, Map<Int, AlternativeCustomization>>()
        for ((key, value) in mutableMap) {
            updatedMap[key] = value
        }

        return ChildItemsCustomization(updatedMap)
    }

    // SelectedChildItemsModifiers will be valid to use with concession
    // item when it has alternative for each package child item with the same quantity
    fun isValidToUseWithConcessionItem(concessionItemDetails: ConcessionItemDetails): Boolean {
        concessionItemDetails.packageChildItems
            .filter { it.alternatives.isNotEmpty() }
            .forEach {
                if (it.quantity != map[it.id]?.size) {
                    return false
                }
            }

        map.values.forEach {
            it.values.forEach { alternativeCustomization ->
                if (!alternativeCustomization.isValidToAddToCart()) {
                    return false
                }
            }
        }

        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        if (this::class != other::class) return false

        other as ChildItemsCustomization

        return map.deepEquals(other.map)
    }

    override fun hashCode(): Int {
        return map.hashCode()
    }
}
