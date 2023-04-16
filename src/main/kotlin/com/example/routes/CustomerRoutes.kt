package com.example.routes

import com.example.dao.CustomersRepository
import com.example.models.CustomerCreation
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val customers = CustomersRepository()

fun Route.customerRouting() {
    route("/customer") {
        get {
            val allCustomers = customers.getAll()
            if (allCustomers.isNotEmpty()) {
                call.respond(allCustomers)
            } else {
                call.respondText("No customers found", status = HttpStatusCode.NotFound)
            }
        }
        get("{id?}") {
            val id =
                call.parameters["id"]?.toIntOrNull() ?: return@get call.respondText(
                    text = "Missing id or id is not an integer",
                    status = HttpStatusCode.BadRequest
                )

            val customer = customers.get(id = id) ?: return@get call.respondText(
                text = "Customer with id: $id was not found!",
                status = HttpStatusCode.NotFound
            )

            call.respond(customer)
        }
        post {
            val customer = call.receive<CustomerCreation>()
            customers.add(customer)

            call.respondText("Customer created successfully", status = HttpStatusCode.Created)
        }
        delete("{id?}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@delete call.respondText(
                text = "Missing id or id is not an integer.",
                status = HttpStatusCode.BadRequest
            )

            if (customers.delete(id)) {
                call.respondText(text = "Customer deleted successfully", status = HttpStatusCode.NoContent)
            } else {
                call.respondText(text = "Customer with id: $id, was not found!", status = HttpStatusCode.NotFound)
            }
        }
    }
}