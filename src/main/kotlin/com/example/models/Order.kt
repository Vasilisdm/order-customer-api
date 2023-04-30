package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class OrderCreation (
    val items: List<OrderItemCreation>
)

@Serializable
data class OrderItemCreation(
    val name: String,
    val quantity: Int,
    val price: Double
)

@Serializable
data class Order(
    val id: Long = 0,
    val items: List<OrderItem>
)
@Serializable
data class OrderItem(
    val id: Long = 0,
    val orderId: Long = 0,
    val name: String,
    val quantity: Int,
    val price: Double
)