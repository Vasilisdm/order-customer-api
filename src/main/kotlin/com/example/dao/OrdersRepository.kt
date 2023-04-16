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

    override suspend fun addOrderItem(orderId: Int, item: OrderItemCreation) = DatabaseFactory.dbQuery {
        val order = OrderEntity[orderId]
        OrderItemEntity.new {
            name = item.name
            amount = item.amount
            price = item.price
            this.order = order
        }.id.value
    }

    override suspend fun add(order: OrderCreation): Int = DatabaseFactory.dbQuery {
        val orderId = OrderEntity.new {
            creationDate = LocalDateTime.now()
        }.id.value

        order.items.forEach {
            addOrderItem(orderId = orderId, it)
        }

        orderId
    }

    override suspend fun delete(id: Int?): Boolean {
        TODO("Not yet implemented")
    }
}