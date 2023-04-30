package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Order(
    val id: Long = 0,
    val creationDate: String,
    val items: List<OrderItem>
)
@Serializable
data class OrderItem(
    val id: Long = 0,
    val orderId: Long = 0,
    val name: String,
    val amount: Int,
    val price: Double
)