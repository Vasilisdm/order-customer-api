package com.example.plugins

import com.example.routes.customerRouting
import com.example.routes.listOrdersRoute
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
        customerRouting()
        listOrdersRoute()
    }
}
