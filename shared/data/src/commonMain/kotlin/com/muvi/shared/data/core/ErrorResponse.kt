package com.muvi.shared.data.core

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val statusCode: Int,
    val code: Int? = null,
    val message: String? = null,
    val data: Map<String, String>? = null,
)
