package com.example.routes

import com.example.models.CustomerCreation
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlin.test.*


class CustomerRoutesKtTest {

    private val customer = CustomerCreation(
        firstName = "John",
        lastName = "Doe",
        email = "johndoe@domain.com"
    )

    @Test
    fun `Customer creation should succeed`() =
        testApplication {
            // given
            val client = createClient {
                install(ContentNegotiation) {
                    json()
                }
            }

            // when
            val customerResponse = client.post("/customer") {
                contentType(ContentType.Application.Json)
                setBody(customer)
            }

            // then
            assertEquals(HttpStatusCode.Created, customerResponse.status)
        }
}
