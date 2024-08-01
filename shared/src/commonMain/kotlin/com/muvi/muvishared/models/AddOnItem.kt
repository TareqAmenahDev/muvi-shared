package com.muvi.muvishared.models

import com.muvi.muvishared.models.ConcessionItem

data class AddOnItem(
    val id: String,
    val name: String,
    val concessionItem: ConcessionItem?,
)
