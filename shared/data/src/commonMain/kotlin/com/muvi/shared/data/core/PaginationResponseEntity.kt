package com.muvi.shared.data.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaginationResponseEntity<T>(
    @SerialName("data")
    val data: List<T>,
    @SerialName("pagination")
    val pagination: PaginationEntity,
)

@Serializable
data class PaginationEntity(
    @SerialName("page")
    val page: Int,
    @SerialName("pageCount")
    val pageCount: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("count")
    val count: Int,
)
