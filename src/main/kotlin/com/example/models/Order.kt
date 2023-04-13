package com.example.models

import kotlinx.serialization.Serializable
@Serializable
data class Order(
    val number: String,
    val content: List<OrderItem>
)
@Serializable
data class OrderItem (
    val item: String,
    val amount: Int,
    val price: Double
)

val orders = mutableListOf<Order>()