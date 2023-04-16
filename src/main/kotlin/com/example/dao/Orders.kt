package com.example.dao

import com.example.models.Order
import com.example.models.OrderCreation
import com.example.models.OrderItem
import com.example.models.OrderItemCreation

interface Orders {
    suspend fun getAll() : List<Order>
    suspend fun get(id: Int?): Order?
    suspend fun add(order: OrderCreation): Int?
    suspend fun addOrderItem(orderId: Int, item: OrderItemCreation): Int
    suspend fun delete(id: Int?): Boolean
}