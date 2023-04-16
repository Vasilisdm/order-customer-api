package com.example.models

import com.example.models.OrderEntity.Companion.referrersOn
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

@Serializable
data class OrderCreation(
    val creationDate: String,
    val items: List<OrderItemCreation>
)

@Serializable
data class OrderItemCreation(
    val name: String,
    val amount: Int,
    val price: Double
)

data class Order(
    val id: Int = 0,
    val creationDate: LocalDateTime,
    val items: List<OrderItem>
)

data class OrderItem(
    val id: Int = 0,
    val name: String,
    val amount: Int,
    val price: Double
)

object OrdersTable : IntIdTable(name = "Orders") {
    val creationDate = datetime("creation_date")
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
    val order by OrderEntity referrersOn OrderItemsTable.order
}

fun OrderItemEntity.toOrderItem() = OrderItem(
    id = id.value,
    name = name,
    amount = amount,
    price = price
)

class OrderEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<OrderEntity>(OrdersTable)
    var creationDate by OrdersTable.creationDate
    val items by OrderItemEntity referrersOn OrderItemsTable.order
}

fun OrderEntity.toOrder() = Order(
    id = id.value,
    creationDate = creationDate,
    items = items.map { it.toOrderItem() }
)

//val orders = mutableListOf<OrderCreation>()