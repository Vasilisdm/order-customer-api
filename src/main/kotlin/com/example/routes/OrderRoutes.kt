package com.example.routes

import com.example.models.Order
import com.example.models.orders
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.listOrdersRoute() {
    route("/order") {
        get {
            if (orders.isNotEmpty()) {
                call.respond(orders)
            } else {
                call.respondText("No orders found.", status = HttpStatusCode.NotFound)
            }
        }
        get("{number?}") {
            val number = call.parameters["number"] ?: return@get call.respondText(
                "No order number.",
                status = HttpStatusCode.BadRequest
            )

            val order = orders.find { it.number == number } ?: return@get call.respondText(
                "Order with id: $number was not found!",
                status = HttpStatusCode.NotFound
            )

            call.respond(order)
        }
        post {
            val order = call.receive<Order>()
            orders.add(order)

            call.respondText("Order successfully created.", status = HttpStatusCode.Created)
        }
        delete("{number?}") {
            val number = call.parameters["number"] ?: return@delete call.respondText(
                "Missing order number",
                status = HttpStatusCode.BadRequest
            )

            if (orders.removeIf { it.number == number }) {
                call.respondText("Order deleted successfully.", status = HttpStatusCode.NoContent)
            } else {
                call.respondText("Order with id: $number was not found!", status = HttpStatusCode.BadRequest)
            }
        }
    }
}