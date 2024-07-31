package com.muvi.muvishared.concessions_cart

import com.muvi.muvishared.models.ConcessionItemDetails

data class CartItem(
    val concessionCartItem: ConcessionItemDetails,
    val isOfferItem: Boolean,
    val quantity: Int,
    val alternativeCustomization: AlternativeCustomization?,
    val modifierGroupsCustomization: ModifierGroupsCustomization?,
    val childItemsCustomization: ChildItemsCustomization?,
    val addToCartTimestamp: Long,
) {
    val totalPrice: Double
        get() = quantity * singleItemPrice

    val singleItemPrice: Double
        get() {
            var result = concessionCartItem.price ?: 0.0

            modifierGroupsCustomization?.getModifierPrices()?.let { value ->
                result += value
            }

            if (concessionCartItem.price == 0.0) {
                alternativeCustomization?.alternativeItem?.price?.let { value ->
                    result += value
                }
            }

            return result
        }

    val showingImageUrl: String?
        get() {

            if (alternativeCustomization != null) {
                return alternativeCustomization.alternativeItem.imageUrl
            }

            return concessionCartItem.imageUrl
        }

    override fun equals(other: Any?): Boolean {
        val otherCartItem = other as? CartItem ?: return false

        return concessionCartItem.id == otherCartItem.concessionCartItem.id &&
                quantity == otherCartItem.quantity &&
                alternativeCustomization == otherCartItem.alternativeCustomization &&
                modifierGroupsCustomization == otherCartItem.modifierGroupsCustomization &&
                childItemsCustomization == otherCartItem.childItemsCustomization
    }

    override fun hashCode(): Int {
        var result = concessionCartItem.hashCode()
        result = 31 * result + quantity
        result = 31 * result + (modifierGroupsCustomization.hashCode())
        result = 31 * result + (childItemsCustomization.hashCode())
        return result
    }
}

fun List<CartItem>.isEquals(otherList: List<CartItem>): Boolean {
    if (this.size != otherList.size) return false
    for (item in this) {
        otherList.firstOrNull { it == item } ?: return false
    }
    return true
}

fun CartItem.isCustomized(): Boolean {
    if (this.alternativeCustomization != null) return true
    if (modifierGroupsCustomization != null && modifierGroupsCustomization.map.isEmpty()) return true
    return childItemsCustomization != null && childItemsCustomization.map.isEmpty()
}
