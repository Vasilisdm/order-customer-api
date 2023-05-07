package com.example.dao.repositories

import com.example.dao.Database
import com.example.dao.interfaces.Orders
import com.example.models.*
import org.jooq.generated.Tables.ORDERITEMS
import org.jooq.generated.Tables.ORDERS
import org.jooq.impl.DSL.*

class OrdersRepository : Orders {

    private val create = Database.getConnection()

    override suspend fun getAll(): List<OrderCreated> =
        create
            .select(
                ORDERS,
                multiset(select().from(ORDERITEMS).where(ORDERITEMS.ORDER_ID.eq(ORDERS.ID)))
                    .`as`("items")
                    .convertFrom { it.into(OrderItemCreated::class.java) })
            .from(ORDERS)
            .fetchInto(OrderCreated::class.java)

    override suspend fun get(id: Long?): OrderCreated? =
        create
            .select(
                ORDERS,
                multiset(select().from(ORDERITEMS).where(ORDERITEMS.ORDER_ID.eq(ORDERS.ID)))
                    .`as`("items")
                    .convertFrom { it.into(OrderItemCreated::class.java) })
            .from(ORDERS)
            .where(ORDERS.ID.eq(id))
            .fetchOneInto(OrderCreated::class.java)

    override suspend fun add(order: OrderCreation): OrderCreated? {
        val orderRecord = create.newRecord(ORDERS)
        orderRecord.store()

        val orderItemsRecords =
            order.items.map { orderItem ->
                val orderItemRecord = create.newRecord(ORDERITEMS)
                orderItemRecord.orderId = orderRecord.id
                orderItemRecord.name = orderItem.name
                orderItemRecord.quantity = orderItem.quantity
                orderItemRecord.price = orderItem.price
                orderItemRecord
            }

        create.insertInto(ORDERS)
            .set(orderRecord)
            .returning(ORDERS.ID)
            .fetchOne().let {
                orderRecord.id = it?.getValue(ORDERS.ID)
            }

        for (orderItemRecord in orderItemsRecords) {
            orderItemRecord.store()
        }

        return get(orderRecord.id)
    }

    override suspend fun delete(id: Long?): Boolean {
        val deletedRows = create.deleteFrom(ORDERS).where(ORDERS.ID.eq(id)).execute()
        return deletedRows > 0
    }
}
