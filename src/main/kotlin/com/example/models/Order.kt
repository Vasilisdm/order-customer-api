package com.example.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

@Serializable
data class Order(
    val id: Int = 0,
    val creationDate: String,
    val items: List<OrderItem>
)
@Serializable
data class OrderItem(
    val id: Int = 0,
    val name: String,
    val amount: Int,
    val price: Double
)

object OrdersTable : IntIdTable(name = "Orders") {
    val creationDate = varchar(name = "creation_date", length = 128)
}

class OrderEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<OrderEntity>(OrdersTable)
    var creationDate by OrdersTable.creationDate
    val items by OrderItemEntity referrersOn OrderItemsTable.order
}

object OrderItemsTable : IntIdTable("OrderItems") {
    val name = varchar(name = "name", length = 512)
    val amount = integer(name = "amount")
    val price = double(name = "double")
    val order = reference(name = "order_id", OrdersTable, onDelete = ReferenceOption.CASCADE)
}

class OrderItemEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<OrderItemEntity>(OrderItemsTable)
    var name by OrderItemsTable.name
    var amount by OrderItemsTable.amount
    var price by OrderItemsTable.price
    var order by OrderEntity referencedOn OrderItemsTable.order
}

fun OrderItemEntity.toOrderItem() = OrderItem(
    id = id.value,
    name = name,
    amount = amount,
    price = price
)

fun OrderEntity.toOrder() = Order(
    id = id.value,
    creationDate = creationDate,
    items = items.map { it.toOrderItem() }
)