package com.example.dao

import com.example.models.OrderCreated
import com.example.models.OrderCreation
import com.example.models.OrderItemCreation


interface Orders {
    suspend fun getAll() : List<OrderCreated>
    suspend fun get(id: Long?): OrderCreated?
    suspend fun add(order: OrderCreation): Long?
    suspend fun addOrderItem(orderId: Long, item: OrderItemCreation): Long
    suspend fun delete(id: Long?): Boolean
}