package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class CustomerCreation(
    val firstName: String,
    val lastName: String,
    val email: String
)

@Serializable
data class CustomerCreated(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String
)

object CustomersTable : Table(name = "Customers") {
    val id = integer(name = "id").autoIncrement()
    val firstName = varchar(name = "firstName", length = 128)
    val lastName = varchar(name = "lastName", length = 128)
    val email = varchar(name = "email", length = 255)

    override val primaryKey = PrimaryKey(id, name = "PK_Customers_ID")
}