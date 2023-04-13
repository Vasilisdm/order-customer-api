package com.example.routes

import com.example.models.orders
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.totalizeOrderRoute() {
    get("order/{orderId?}/total") {
        val orderId = call.parameters["orderId"] ?: return@get call.respondText(
            "No order id!",
            status = HttpStatusCode.BadRequest
        )

        val order = orders.find { it.id == orderId } ?: return@get call.respondText(
            "Order with id:$orderId was not found!",
            status = HttpStatusCode.NotFound
        )

        call.respond(order.content.sumOf { it.amount * it.price })
    }
}