package com.example.dao

import com.example.models.Order
import com.example.models.OrderItem

interface Orders {
    suspend fun getAll() : List<Order>
    suspend fun get(id: Int?): Order?
    suspend fun add(order: Order): Int?
    suspend fun addOrderItem(orderId: Int, item: OrderItem): Int
    suspend fun delete(id: Int?): Boolean
}