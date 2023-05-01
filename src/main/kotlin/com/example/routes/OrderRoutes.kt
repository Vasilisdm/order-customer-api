package com.example.routes

import com.example.dao.OrdersRepository
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
            TODO("Fix order deletion")
//            val orderId = call.parameters["orderId"]?.toIntOrNull() ?: return@delete call.respondText(
//                "Missing order id",
//                status = HttpStatusCode.BadRequest
//            )
//
//            if (orders.removeIf { it.id == orderId }) {
//                call.respondText("Order deleted successfully.", status = HttpStatusCode.NoContent)
//            } else {
//                call.respondText("Order with id: $orderId was not found!", status = HttpStatusCode.BadRequest)
//            }
        }
    }
}