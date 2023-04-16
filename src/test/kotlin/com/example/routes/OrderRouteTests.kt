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

//
//    private val orderItems = listOf(
//        OrderItemCreation(item = 1, amount = 1, price = 159.99),
//        OrderItemCreation(item = 2, amount = 1, price = 79.99),
//        OrderItemCreation(item = 3, amount = 1, price = 129.99),
//        OrderItemCreation(item = 4, amount = 1, price = 119.00),
//    )
//
//    private val order = OrderCreation(id = 1, content = orderItems)
//
//    @Test
//    fun `Post order should execute successfully`() = testApplication {
//        // given
//        val client = createClient {
//            install(ContentNegotiation) {
//                json()
//            }
//        }
//
//        // when
//        val response = client.post("/order") {
//            contentType(ContentType.Application.Json)
//            setBody(order)
//        }
//
//        // then
//        assertEquals(HttpStatusCode.Created, response.status)
//    }
}