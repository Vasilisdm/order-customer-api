package com.example.dao

import com.example.models.OrderCreated
import com.example.models.OrderCreation
import com.example.models.OrderItemCreation


interface Orders {
    suspend fun getAll() : List<OrderCreated>
    suspend fun get(id: Int?): OrderCreated?
    suspend fun add(order: OrderCreation): Int?
    suspend fun addOrderItem(orderId: Int, item: OrderItemCreation): Int
    suspend fun delete(id: Int?): Boolean
}