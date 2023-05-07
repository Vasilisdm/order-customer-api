package com.example.dao

import com.example.models.OrderCreated
import com.example.models.OrderCreation

interface Orders {
    suspend fun getAll() : List<OrderCreated>
    suspend fun get(id: Long?): OrderCreated?
    suspend fun add(order: OrderCreation): OrderCreated?
    suspend fun delete(id: Long?): Boolean
}