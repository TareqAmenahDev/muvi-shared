package com.muvi.muvishared.models

data class ModifiersGroup(
    val id: String,
    val name: String?,
    val vistaId: String?,
    val maximumQuantity: Int?,
    val minimumQuantity: Int?,
    val modifiers: List<ModifierItem>,
)

data class ModifierItem(
    val id: String,
    val name: String?,
    val price: Double?,
    val isOutOfStock: Boolean,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        if (this::class != other::class) return false

        other as ModifierItem

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
