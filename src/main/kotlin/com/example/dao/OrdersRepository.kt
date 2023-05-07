package com.example.dao

import com.example.models.*
import org.jooq.generated.Tables.ORDERITEMS
import org.jooq.generated.Tables.ORDERS
import org.jooq.impl.DSL.multiset
import org.jooq.impl.DSL.select

class OrdersRepository : Orders {

    private val create = Database.getConnection()

    override suspend fun getAll(): List<OrderCreated> = create.select(ORDERS,
        multiset(
            select()
                .from(ORDERITEMS)
                .where(ORDERITEMS.ORDER_ID.eq(ORDERS.ID))
        ).`as`("items").convertFrom { it.into(OrderItemCreated::class.java) }
    ).from(ORDERS)
        .fetchInto(OrderCreated::class.java)

    override suspend fun get(id: Long?): OrderCreated? = create.select(ORDERS,
        multiset(
            select()
                .from(ORDERITEMS)
                .where(ORDERITEMS.ORDER_ID.eq(ORDERS.ID))
        ).`as`("items").convertFrom { it.into(OrderItemCreated::class.java) }
    ).from(ORDERS)
        .where(ORDERS.ID.eq(id))
        .fetchOneInto(OrderCreated::class.java)

    override suspend fun add(order: OrderCreation): Long? {
        TODO("Not yet implemented")
    }

    override suspend fun addOrderItem(orderId: Long, item: OrderItemCreation): Long {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: Long?): Boolean {
        val deletedRows = create.deleteFrom(ORDERS).where(ORDERS.ID.eq(id)).execute()
        return deletedRows > 0
    }

}