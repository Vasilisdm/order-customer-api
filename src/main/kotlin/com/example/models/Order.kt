package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Order(
    val id: Int = 0,
    val creationDate: String,
    val items: List<OrderItem>
)
@Serializable
data class OrderItem(
    val id: Int = 0,
    val name: String,
    val amount: Int,
    val price: Double
)