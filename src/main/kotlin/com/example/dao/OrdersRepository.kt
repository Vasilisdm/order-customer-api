package com.example.dao

import com.example.models.*
import org.jooq.generated.Tables.ITEMS
import org.jooq.generated.Tables.ORDERS

private val create = Database.getConnection()

class OrdersRepository : Orders {
    override suspend fun getAll(): List<OrderCreated> = create.select()
        .from(ORDERS)
        .innerJoin(ITEMS).on(ORDERS.ID.eq(ITEMS.ORDER_ID))
        .fetchInto(OrderCreated::class.java)

    override suspend fun get(id: Int?): OrderCreated? {
        TODO("Not yet implemented")
    }

    override suspend fun add(order: OrderCreation): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun addOrderItem(orderId: Int, item: OrderItemCreation): Int {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: Int?): Boolean {
        TODO("Not yet implemented")
    }

}