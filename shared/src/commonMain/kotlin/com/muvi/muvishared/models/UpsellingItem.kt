package com.muvi.muvishared.models

data class UpsellingItem(
    val id: String,
    val concessionItemId: String,
    val sort: Int,
    val isDefault: Boolean,
    val size: UpsellingItemSize,
    val concessionItem: ConcessionItem,
)

enum class UpsellingItemSize(val weight: Int) {
    Small(1),
    Medium(2),
    Large(3),
}

data class UpsellingGroup(
    val parentConcessionItemId: String,
    val name: String,
)
