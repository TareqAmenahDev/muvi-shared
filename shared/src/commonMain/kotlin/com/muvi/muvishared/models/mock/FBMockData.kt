package com.muvi.muvishared.models.mock

import com.muvi.muvishared.models.AddOnItem
import com.muvi.muvishared.models.AlternativeItem
import com.muvi.muvishared.models.ConcessionItem
import com.muvi.muvishared.models.ConcessionItemDetails
import com.muvi.muvishared.models.ConcessionTab
import com.muvi.muvishared.models.ModifierItem
import com.muvi.muvishared.models.ModifiersGroup
import com.muvi.muvishared.models.UpsellingItem
import com.muvi.muvishared.models.UpsellingItemSize
import com.muvi.muvishared.concessions_cart.AlternativeCustomization
import com.muvi.muvishared.concessions_cart.CartItem
import com.muvi.muvishared.concessions_cart.ChildItemsCustomization
import com.muvi.muvishared.concessions_cart.ModifiersCustomization
import kotlin.random.Random

class FBMockData {
    val mockConcessionTabs =
        listOf(
            ConcessionTab(id = "1", name = "Contab1", imageUrl = null),
            ConcessionTab(id = "2", name = "tab 2", imageUrl = null),
            ConcessionTab(id = "3", name = "Conces tab 3", imageUrl = null),
            ConcessionTab(id = "4", name = "Concessionnnnn tab 4", imageUrl = null),
            ConcessionTab(id = "5", name = "Concessionn tab 5", imageUrl = null),
            ConcessionTab(id = "6", name = "Concession tab 6", imageUrl = null),
            ConcessionTab(id = "7", name = "Concession tab 7", imageUrl = null),
            ConcessionTab(id = "8", name = "Concession tab 8", imageUrl = null),
            ConcessionTab(id = "9", name = "Concession tab 9", imageUrl = null),
        )

    val mockDetailsConcessionItemsList =
        (1..10).map {
            ConcessionItemDetails(
                id = it.toString(),
                name = "item $it",
                description = "Some description here",
                imageUrl = null,
                price = 10.0,
                calories = 10,
                isOutOfStock = true,
                isVegan = Random.nextBoolean(),
                modifierGroups = listOf(),
                packageChildItems = listOf(),
                upsellingItems = listOf(),
                addOns = listOf(),
                alternatives = listOf(),
                upsellingGroup = null,
            )
        }.toList()

    val modifiersList =
        listOf(
            ModifierItem(
                id = "1",
                name = "Modifier 1",
                price = 30.0,
            ),
            ModifierItem(
                id = "2",
                name = "Modifier 2",
                price = 30.0,
            ),
            ModifierItem(
                id = "3",
                name = "Modifier 3",
                price = 30.0,
            ),
            ModifierItem(
                id = "4",
                name = "Modifier 4",
                price = 30.0,
            ),
        )

    val alternativesList =
        listOf(
            AlternativeItem(
                id = "1",
                name = "Alternative 1",
                price = 30.0,
                imageUrl = "",
                calories = 3,
                isVegan = true,
                modifierGroups =
                    ModifiersGroup(
                        id = "4362",
                        name = "Mix modifiers",
                        vistaId = "2",
                        maximumQuantity = 2,
                        minimumQuantity = 2,
                        modifiers = modifiersList,
                    ),
            ),
            AlternativeItem(
                id = "2",
                imageUrl = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg",
                calories = 5,
                isVegan = true,
                name = "Alternative 2",
                price = 30.0,
                modifierGroups = null,
            ),
            AlternativeItem(
                id = "3",
                name = "Alternative 3",
                price = 30.0,
                imageUrl = "https://images.pexels.com/photos/414612/pexels-photo-414612.jpeg?cs=srgb&dl=pexels-james-wheeler-414612.jpg&fm=jpg",
                calories = 3,
                isVegan = true,
                modifierGroups = null,
            ),
        )

    val concessionItemDetails =
        ConcessionItemDetails(
            id = "1",
            name = "item name",
            description = "Some description here",
            imageUrl = null,
            price = 0.0,
            calories = 10,
            isVegan = Random.nextBoolean(),
            modifierGroups =
                listOf(
//            ModifiersGroup(
//                id = 1,
//                name = "Modifier Group 1",
//                vistaId = "12",
//                maximumQuantity = 3,
//                minimumQuantity = 3,
//                modifiers = (1..4).map {
//                    ModifierItem(id = it, name = "Modifier $it", price = 10.0)
//                }
//            ),
//            ModifiersGroup(
//                id = 2,
//                name = "Modifier Group 2",
//                vistaId = "12",
//                maximumQuantity = 2,
//                minimumQuantity = 2,
//                modifiers = (1..4).map {
//                    ModifierItem(id = it, name = "Modifier $it", price = 10.0)
//                }
//            )
                ),
            packageChildItems =
                listOf(
//            PackageChildItem(
//                id = 1,
//                name = "small drink",
//                quantity = 2,
//                alternatives = alternativesList,
//                concessionItemId = 1
//            ),
//            PackageChildItem(
//                id = 2,
//                name = "popcorn",
//                quantity = 1,
//                alternatives = listOf(),
//                concessionItemId = 2
//            )
                ),
            upsellingItems =
                (1..3).map {
                    UpsellingItem(
                        id = it.toString(),
                        concessionItemId = "1",
                        sort = it,
                        isDefault = false,
                        size =
                            when (it) {
                                1 -> UpsellingItemSize.Small
                                2 -> UpsellingItemSize.Medium
                                3 -> UpsellingItemSize.Large
                                else -> UpsellingItemSize.Small
                            },
                        concessionItem =
                            ConcessionItem(
                                id = (it - 1).toString(),
                                price = 2.3 * it,
                                name = null,
                                isOutOfStock = false,
                                description = null,
                                imageUrl = null,
                                calories = null,
                                isVegan = null,
                                alternatives = listOf(),
                                upsellingGroup = null,
                            ),
                    )
                },
            addOns =
                (1..5).map {
                    AddOnItem(
                        id = it.toString(),
                        name = "Max Oneal $it",
                        concessionItem = mockDetailsConcessionItemsList[it],
                    )
                },
            alternatives = alternativesList,
//        alternatives = listOf(),
            isOutOfStock = false,
            upsellingGroup = null,
        )

    val alternativeCustomization =
        AlternativeCustomization(
            alternativeItem = alternativesList[0],
            modifiersCustomization =
                ModifiersCustomization(
                    mapOf(
                        0 to ModifierItem("1", "modifier1", 10.0),
                        1 to ModifierItem("2", "modifier2", 10.0),
                    ),
                ),
        )
    val alternativeCustomization2 =
        AlternativeCustomization(
            alternativeItem = alternativesList[1],
            modifiersCustomization =
                ModifiersCustomization(
                    mapOf(
                        0 to ModifierItem("1", "mix1", 10.0),
                        1 to ModifierItem("2", "mix2", 10.0),
                    ),
                ),
        )
    val cartItem =
        CartItem(
            concessionCartItem = concessionItemDetails,
            quantity = 2,
            isOfferItem = false,
            alternativeCustomization = null,
            childItemsCustomization =
                ChildItemsCustomization(
                    mapOf(
                        "1" to
                            mapOf(
                                0 to alternativeCustomization,
                                1 to alternativeCustomization2,
                            ),
                        "2" to
                            mapOf(
                                0 to alternativeCustomization,
                                1 to alternativeCustomization2,
                            ),
                    ),
                ),
            modifierGroupsCustomization = null,
            addToCartTimestamp = 1000,
        )

    val cartItem2 =
        CartItem(
            concessionCartItem = concessionItemDetails.copy(name = "Item 2"),
            quantity = 2,
            isOfferItem = true,
            alternativeCustomization = null,
            childItemsCustomization =
                ChildItemsCustomization(
                    mapOf(
                        "1" to
                            mapOf(
                                0 to alternativeCustomization,
                                1 to alternativeCustomization2,
                            ),
                        "2" to
                            mapOf(
                                0 to alternativeCustomization,
                                1 to alternativeCustomization2,
                            ),
                    ),
                ),
            modifierGroupsCustomization = null,
            addToCartTimestamp = 1000,
        )
}
