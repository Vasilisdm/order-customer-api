package com.example.routes

import com.example.dao.repositories.OrdersRepository
import com.example.models.OrderCreation
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val orders = OrdersRepository()

fun Route.listOrdersRoute() {
    route("/order") {
        get {
            val orders = orders.getAll()

            if (orders.isNotEmpty()) {
                call.respond(orders)
            } else {
                call.respondText("No orders found.", status = HttpStatusCode.NotFound)
            }
        }
        get("{orderId?}") {
            val orderId = call.parameters["orderId"]?.toLongOrNull() ?: return@get call.respondText(
                "No order id.",
                status = HttpStatusCode.BadRequest
            )

            val order = orders.get(id = orderId) ?: return@get call.respondText(
                "Order with id: $orderId was not found!",
                status = HttpStatusCode.NotFound
            )

            call.respond(order)
        }
        post {
            val order = call.receive<OrderCreation>()
            orders.add(order)

            call.respondText("Order successfully created.", status = HttpStatusCode.Created)
        }
        delete("{orderId?}") {
            val orderId = call.parameters["orderId"]?.toLongOrNull() ?: return@delete call.respondText(
                text = "No order id provided.",
                status = HttpStatusCode.BadRequest
            )

            if (orders.delete(orderId)) {
                call.respondText(text = "Order with id: $orderId deleted successfully.")
            } else {
                call.respondText(text = "Order with id: $orderId could not be found.")
            }
        }
    }
}