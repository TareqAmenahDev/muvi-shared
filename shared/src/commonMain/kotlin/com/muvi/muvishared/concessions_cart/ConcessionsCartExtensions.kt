package com.muvi.muvishared.concessions_cart

import com.muvi.muvishared.models.ConcessionItem

fun List<CartItem>.getConcessionItemQuantity(itemId: String): Int {
    return this.filter { it.concessionCartItem.id == itemId }.sumOf { it.quantity }
}

fun List<CartItem>.getConcessionItemQuantityByUpsellingGroup(
    concessionItem: ConcessionItem,
): Int {
    return this.filter {
        val parentConcessionItemId = it.concessionCartItem.upsellingGroup?.parentConcessionItemId

        it.concessionCartItem.id == concessionItem.id ||
                // if the item has is upselling then group the items by the parentConcessionItemId
                (
                        parentConcessionItemId != null && parentConcessionItemId
                                == concessionItem.upsellingGroup?.parentConcessionItemId
                        )
    }.sumOf { it.quantity }
}
