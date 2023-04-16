package com.example.routes

import io.ktor.server.routing.*

fun Route.totalizeOrderRoute() {
    get("order/{orderId?}/total") {
        TODO("Fix order total")
//        val orderId = call.parameters["orderId"]?.toIntOrNull() ?: return@get call.respondText(
//            "No order id!",
//            status = HttpStatusCode.BadRequest
//        )
//
//        val order = orders.find { it.id == orderId } ?: return@get call.respondText(
//            "Order with id:$orderId was not found!",
//            status = HttpStatusCode.NotFound
//        )
//
//        call.respond(order.content.sumOf { it.amount * it.price })
    }
}