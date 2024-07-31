package com.muvi.muvishared.models

import com.muvi.muvishared.concessions_cart.AlternativeCustomization
import com.muvi.muvishared.concessions_cart.ChildItemsCustomization
import com.muvi.muvishared.concessions_cart.ModifierGroupsCustomization
import com.muvi.muvishared.concessions_cart.getModifierPrices


open class ConcessionItem(
    open val id: String,
    open val name: String?,
    open val description: String?,
    open val imageUrl: String?,
    open val price: Double?,
    open val calories: Int?,
    open val isVegan: Boolean?,
    open val isOutOfStock: Boolean,
    open val alternatives: List<AlternativeItem>,
    open val upsellingGroup: UpsellingGroup?,
) {
    val startingFromPrice: Double
        get() {
            return if (price != null && price!! > 0) {
                price!!
            } else {
                alternatives.minOfOrNull { it.price ?: Double.MAX_VALUE } ?: 0.0
            }
        }

    // if the item is upselling item then we should show the upselling group name
    val showingName: String
        get() = upsellingGroup?.name ?: name ?: ""
}

data class ConcessionItemDetails(
    override val id: String,
    override val name: String?,
    override val description: String?,
    override val imageUrl: String?,
    override val price: Double?,
    override val calories: Int?,
    override val isVegan: Boolean?,
    override val isOutOfStock: Boolean,
    override val alternatives: List<AlternativeItem>,
    override val upsellingGroup: UpsellingGroup?,
    val modifierGroups: List<ModifiersGroup>,
    val packageChildItems: List<PackageChildItem>,
    val upsellingItems: List<UpsellingItem>,
    val addOns: List<AddOnItem>,
) : ConcessionItem(
        id = id,
        name = name,
        description = description,
        imageUrl = imageUrl,
        price = price,
        calories = calories,
        isVegan = isVegan,
        isOutOfStock = isOutOfStock,
        alternatives = alternatives,
        upsellingGroup = upsellingGroup,
    ) {
    val shouldUseStartingFromLabel: Boolean
        get() {
            if (price?.equals(0.0) == true) {
                return true
            }

            modifierGroups.forEach { modifiersGroup ->
                modifiersGroup.modifiers.forEach { modifierItem ->
                    modifierItem.price?.let { price ->
                        if (price > 0) {
                            return true
                        }
                    }
                }
            }

            return upsellingItems.isNotEmpty()
        }

    fun isCustomizationValidToUseWithConcessionItem(
        selectedModifierGroupsCustomization: ModifierGroupsCustomization,
        selectedPackageChileItemsCustomization: ChildItemsCustomization,
        selectedAlternative: AlternativeCustomization?,
    ): Boolean {
        return if (modifierGroups.isNotEmpty() &&
            !selectedModifierGroupsCustomization.isValidToUseWithConcessionItem(
                this,
            )
        ) {
            false
        } else if (packageChildItems.isNotEmpty() &&
            !selectedPackageChileItemsCustomization.isValidToUseWithConcessionItem(
                this,
            )
        ) {
            false
        } else {
            !(
                alternatives.isNotEmpty() &&
                    (
                        selectedAlternative == null ||
                            !selectedAlternative.isValidToAddToCart()
                    )
            )
        }
    }

    fun getTotalPrice(
        itemCount: Int,
        selectedModifierGroupsCustomization: ModifierGroupsCustomization,
        selectedAlternative: AlternativeCustomization?,
    ): Double {
        var result = price ?: 0.0

        selectedModifierGroupsCustomization.getModifierPrices().let { value ->
            result += value
        }

        if (price == 0.0) {
            selectedAlternative?.alternativeItem?.price?.let { value ->
                result += value
            }
        }
        return itemCount * result
    }
}

fun ConcessionItem.toConcessionItemDetails() =
    ConcessionItemDetails(
        id = this.id,
        name = this.name,
        description = this.description,
        imageUrl = this.imageUrl,
        price = this.price,
        calories = this.calories,
        isVegan = this.isVegan,
        isOutOfStock = this.isOutOfStock,
        modifierGroups = listOf(),
        alternatives = listOf(),
        packageChildItems = listOf(),
        upsellingItems = listOf(),
        addOns = listOf(),
        upsellingGroup = this.upsellingGroup,
    )
