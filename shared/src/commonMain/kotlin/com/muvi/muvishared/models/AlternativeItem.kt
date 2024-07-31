package com.muvi.muvishared.models

data class AlternativeItem(
    val id: String,
    val name: String?,
    val imageUrl: String?,
    val price: Double?,
    val calories: Int?,
    val isVegan: Boolean?,
    val modifierGroups: ModifiersGroup?,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        if (this::class != other::class) return false

        other as AlternativeItem

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
