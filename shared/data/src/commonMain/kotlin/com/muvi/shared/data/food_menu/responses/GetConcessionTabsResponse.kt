package com.muvi.data.food_menu.responses

import com.muvi.data.food_menu.entities.ConcessionTabEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetConcessionTabsResponse(
    @SerialName("concessionTabs")
    val concessionTabs: List<ConcessionTabEntity>,
)
