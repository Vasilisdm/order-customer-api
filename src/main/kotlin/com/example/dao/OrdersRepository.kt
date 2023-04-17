package com.example.dao

import com.example.models.*
import java.time.LocalDateTime

class OrdersRepository : Orders {
    override suspend fun getAll(): List<Order> {
        TODO("Not yet implemented")
    }

    override suspend fun get(id: Int?): Order? {
        TODO("Not yet implemented")
    }

    override suspend fun add(order: Order): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun addOrderItem(orderId: Int, item: OrderItem): Int {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: Int?): Boolean {
        TODO("Not yet implemented")
    }

}