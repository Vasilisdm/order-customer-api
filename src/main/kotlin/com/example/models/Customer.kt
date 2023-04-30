package com.example.models

import kotlinx.serialization.Serializable


@Serializable
data class CustomerCreation(
    val firstName: String,
    val lastName: String,
    val email: String
)

@Serializable
data class CustomerCreated(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String
)