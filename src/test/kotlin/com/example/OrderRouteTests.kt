package com.example

import com.example.models.Order
import com.example.models.OrderItem
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlin.test.*

class OrderRouteTests {

    private val orderItems = listOf(
        OrderItem(item = "MX MECHANICAL MINI FOR MAC", amount = 1, price = 159.99),
        OrderItem(item = "LIFT FOR MAC", amount = 1, price = 79.99),
        OrderItem(item = "MX MASTER 3S FOR MAC", amount = 1, price = 129.99),
        OrderItem(item = "MX KEYS MINI FOR MAC", amount = 1, price = 119.00),
    )

    private val order = Order(id = "100", content = orderItems)

//    @Test
//    fun `Post order should execute successfully`() = testApplication {
//        // when-then
//        val client = createClient {
//            install(ContentNegotiation) {
//                json()
//            }
//        }
//
//        val response = client.post("/order") {
//            contentType(ContentType.Application.Json)
//            setBody(order)
//        }
//
//        assertEquals(HttpStatusCode.Created, response.status)
//    }
}