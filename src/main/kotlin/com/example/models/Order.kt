package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class OrderCreation(
    val items: List<OrderItemCreation>
)

@Serializable
data class OrderItemCreation(
    val name: String,
    val quantity: Int,
    val price: Double
)

@Serializable
data class OrderCreated(
    val id: Long,
    val items: List<OrderItemCreated>
)

@Serializable
data class OrderItemCreated(
    val id: Long,
    val orderId: Long,
    val name: String,
    val quantity: Int,
    val price: Double
)