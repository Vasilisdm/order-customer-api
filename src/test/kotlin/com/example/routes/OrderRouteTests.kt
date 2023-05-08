package com.example.routes

import com.example.models.OrderCreation
import com.example.models.OrderItemCreation
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlin.test.*

class OrderRouteTests {

    private val orderItems = listOf(
        OrderItemCreation(name = "OrderItem1", quantity = 1, price = 50.00),
        OrderItemCreation(name = "OrderItem2", quantity = 1, price = 100.00),
        OrderItemCreation(name = "OrderItem3", quantity = 1, price = 150.00),
        OrderItemCreation(name = "OrderItem4", quantity = 1, price = 200.00),
    )

    private val order = OrderCreation(items = orderItems)

    @Test
    fun `Post order should execute successfully`() = testApplication {
        // given
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        // when
        val response = client.post("/order") {
            contentType(ContentType.Application.Json)
            setBody(order)
        }

        // then
        assertEquals(HttpStatusCode.Created, response.status)
    }
}