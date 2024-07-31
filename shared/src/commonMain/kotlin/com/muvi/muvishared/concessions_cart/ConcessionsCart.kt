package com.muvi.muvishared.concessions_cart

import com.muvi.muvishared.models.ConcessionItem
import com.muvi.muvishared.models.ConcessionItemDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ConcessionsCart(
    private val coroutineScope: CoroutineScope,
) {
    private val concessionsCartList = ConcessionsCartList()

    fun itemsList(): List<CartItem> = concessionsCartList.itemsList()

    private val _itemsListAsState: MutableStateFlow<List<CartItem>> = MutableStateFlow(listOf())
    val itemsListAsState =
        _itemsListAsState.asStateFlow() // publicly exposed as read-only state flow

    private val _totalPriceAsState: MutableStateFlow<Double> = MutableStateFlow(concessionsCartList.totalPrice)
    val totalPriceAsState =
        _totalPriceAsState.asStateFlow() // publicly exposed as read-only state flow

    private val _totalCountAsState: MutableStateFlow<Int> = MutableStateFlow(concessionsCartList.getItemsCount())
    val totalCountAsState =
        _totalCountAsState.asStateFlow() // publicly exposed as read-only state flow

    init {
        coroutineScope.launch {
            _itemsListAsState.collect {
                _totalPriceAsState.value = concessionsCartList.totalPrice
                _totalCountAsState.value = _itemsListAsState.value.sumOf { it.quantity }
            }
        }
    }

    fun addItem(
        concessionCartItem: ConcessionItemDetails,
        quantity: Int,
        alternativeCustomization: AlternativeCustomization?,
        modifierGroupsCustomization: ModifierGroupsCustomization?,
        childItemsCustomization: ChildItemsCustomization?,
        isOfferItem: Boolean = false,
    ) {
        concessionsCartList.addItem(
            concessionCartItem = concessionCartItem,
            quantity = quantity,
            alternativeCustomization = alternativeCustomization,
            modifierGroupsCustomization = modifierGroupsCustomization,
            childItemsCustomization = childItemsCustomization,
            isOfferItem = isOfferItem,
        )

        _itemsListAsState.value = itemsList()
    }

    fun addCartItemsList(foodItems: List<CartItem>) {
        concessionsCartList.addCartItemsList(foodItems)
        _itemsListAsState.value = itemsList()
    }

    fun removeCartItem(cartItem: CartItem) {
        concessionsCartList.removeCartItem(cartItem)
        _itemsListAsState.value = itemsList()
    }

    fun removeConcessionItem(concessionItemDetails: ConcessionItem) {
        concessionsCartList.removeConcessionItem(concessionItemDetails)
        _itemsListAsState.value = itemsList()
    }

    fun getItemsCount(): Int {
        return concessionsCartList.getItemsCount()
    }

    fun getConcessionItemQuantity(itemId: String): Int? {
        return concessionsCartList.getConcessionItemQuantity(itemId)
    }

    fun clearCart() {
        concessionsCartList.clearCart()
        _itemsListAsState.value = itemsList()
    }

    fun replaceConcessionItemCartList(
        newItems: List<CartItem>,
        removeItemCondition: (item: CartItem) -> Boolean,
    ) {
        concessionsCartList.replaceConcessionItemCartList(
            newItems = newItems,
            removeItemCondition = removeItemCondition,
        )
        _itemsListAsState.value = itemsList()
    }
}

class ConcessionsCartList {
    private var _itemsList: List<CartItem> = listOf()

    fun itemsList(): List<CartItem> = _itemsList

    val totalPrice: Double
        get() = _itemsList.sumOf { it.totalPrice }

    fun addItem(
        concessionCartItem: ConcessionItemDetails,
        quantity: Int,
        alternativeCustomization: AlternativeCustomization?,
        modifierGroupsCustomization: ModifierGroupsCustomization?,
        childItemsCustomization: ChildItemsCustomization?,
        isOfferItem: Boolean = false,
    ) {
        val currentItemIfExist =
            _itemsList.firstOrNull {
                it.concessionCartItem.id == concessionCartItem.id && modifierGroupsCustomization == it.modifierGroupsCustomization && childItemsCustomization == it.childItemsCustomization && alternativeCustomization == it.alternativeCustomization
            }

        _itemsList =
            if (currentItemIfExist != null) {
                val updatedItem =
                    currentItemIfExist.copy(quantity = currentItemIfExist.quantity + quantity)
                _itemsList.filter { it != currentItemIfExist }.toList() + updatedItem
            } else {
                // TODO: enhance "add item to cart timestamp"
//                val timestamp = Clock.System.now().toEpochMilliseconds()
                val timestamp = ( _itemsList.maxByOrNull { it.addToCartTimestamp }?.addToCartTimestamp ?: 0 ) + 1

                val createdCartItem =
                    CartItem(
                        concessionCartItem = concessionCartItem,
                        quantity = quantity,
                        isOfferItem = isOfferItem,
                        alternativeCustomization = alternativeCustomization,
                        modifierGroupsCustomization = modifierGroupsCustomization,
                        childItemsCustomization = childItemsCustomization,
                        addToCartTimestamp = timestamp,
                    )
                _itemsList + createdCartItem
            }
    }

    fun addCartItemsList(foodItems: List<CartItem>) {
        // TODO: enhance "add item to cart timestamp"
//        val timestamp = Clock.System.now().toEpochMilliseconds()
        val timestamp = ( foodItems.maxByOrNull { it.addToCartTimestamp }?.addToCartTimestamp ?: 0 ) + 1

        foodItems.forEachIndexed { index, foodCartItem ->
            val currentItemIfExist = _itemsList.firstOrNull { it == foodCartItem }

            _itemsList =
                if (currentItemIfExist != null) {
                    val updatedItem =
                        currentItemIfExist.copy(quantity = currentItemIfExist.quantity + foodCartItem.quantity)
                    _itemsList.filter { it != currentItemIfExist }.toList() + updatedItem
                } else {
                    val createdCartItem =
                        CartItem(
                            concessionCartItem = foodCartItem.concessionCartItem,
                            quantity = foodCartItem.quantity,
                            isOfferItem = foodCartItem.isOfferItem,
                            alternativeCustomization = foodCartItem.alternativeCustomization,
                            modifierGroupsCustomization = foodCartItem.modifierGroupsCustomization,
                            childItemsCustomization = foodCartItem.childItemsCustomization,
                            addToCartTimestamp = timestamp + index,
                        )
                    _itemsList + createdCartItem
                }
        }
    }

    fun removeCartItem(cartItem: CartItem) {
        val currentItemIfExist = _itemsList.firstOrNull { it == cartItem }
        currentItemIfExist?.let {
            _itemsList =
                if (currentItemIfExist.quantity == 1) {
                    _itemsList.filter { it != currentItemIfExist }
                } else {
                    val updatedItem =
                        currentItemIfExist.copy(quantity = currentItemIfExist.quantity - 1)
                    _itemsList.filter { it != currentItemIfExist }.toList() + updatedItem
                }
        }
    }

    fun removeConcessionItem(concessionItemDetails: ConcessionItem) {
        val currentItemIfExist =
            _itemsList.firstOrNull { it.concessionCartItem.id == concessionItemDetails.id }
        currentItemIfExist?.let {
            _itemsList =
                if (currentItemIfExist.quantity == 1) {
                    _itemsList.filter { it != currentItemIfExist }
                } else {
                    val updatedItem =
                        currentItemIfExist.copy(quantity = currentItemIfExist.quantity - 1)
                    _itemsList.filter { it != currentItemIfExist }.toList() + updatedItem
                }
        }
    }

    fun getItemsCount(): Int {
        return _itemsList.size
    }

    fun getConcessionItemQuantity(itemId: String): Int? {
        return _itemsList.firstOrNull { it.concessionCartItem.id == itemId }?.quantity
    }

    fun clearCart() {
        _itemsList = listOf()
    }

    fun replaceConcessionItemCartList(
        newItems: List<CartItem>,
        removeItemCondition: (item: CartItem) -> Boolean,
    ) {
        // first remove all cart items that fit the condition passed
        _itemsList = _itemsList.filterNot(removeItemCondition)
        // then add the new Items list passed
        _itemsList = _itemsList.plus(newItems)
    }
}
