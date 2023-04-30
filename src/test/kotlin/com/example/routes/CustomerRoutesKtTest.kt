package com.example.routes

import com.example.dao.Database
import com.example.models.CustomerCreation
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import org.jooq.generated.Tables.CUSTOMERS
import org.junit.After
import kotlin.test.*


class CustomerRoutesKtTest {

    private val create = Database.getConnection()

    private val testCustomer = CustomerCreation(
        firstName = "Robert",
        lastName = "Doe",
        email = "robertdoe@domain.com"
    )

    @After
    fun cleanUp() {
        create.deleteFrom(CUSTOMERS).where(CUSTOMERS.EMAIL.eq(testCustomer.email)).execute()
    }

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
                setBody(testCustomer)
            }

            // then
            assertEquals(HttpStatusCode.Created, customerResponse.status)
        }
}
