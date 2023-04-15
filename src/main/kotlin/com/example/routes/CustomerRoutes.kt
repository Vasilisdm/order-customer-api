package com.example.routes

import com.example.dao.DAOFacadeImpl
import com.example.models.Customer
import com.example.models.customers
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val dao = DAOFacadeImpl()

fun Route.customerRouting() {
    route("/customer") {
        get {
            if (dao.allCustomers().isNotEmpty()) {
                call.respond(customers)
            } else {
                call.respondText("No customers found", status = HttpStatusCode.NotFound)
            }
        }
        get("{id?}") {
            val id =
                call.parameters["id"]?.toIntOrNull() ?: return@get call.respondText(
                    "Missing id",
                    status = HttpStatusCode.BadRequest
                )

            val customer = customers.find { it.id == id } ?: return@get call.respondText(
                "Customer with id: $id was not found!",
                status = HttpStatusCode.NotFound
            )

            call.respond(customer)
        }
        post {
            val customer = call.receive<Customer>()
            customers.add(customer)

            call.respondText("Customer created successfully", status = HttpStatusCode.Created)
        }
        delete("{id?}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@delete call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )

            if (customers.removeIf { it.id == id }) {
                call.respondText("Customer deleted successfully", status = HttpStatusCode.NoContent)
            } else {
                call.respondText("Customer with id: $id, was not found!", status = HttpStatusCode.NotFound)
            }

        }
    }
}