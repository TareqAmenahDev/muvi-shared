package com.muvi.muvishared.models

data class PackageChildItem(
    val id: String,
    val name: String?,
    val quantity: Int?,
    val concessionItemId: String?,
    val alternatives: List<AlternativeItem>,
)
