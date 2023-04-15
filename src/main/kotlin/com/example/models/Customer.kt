package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*

@Serializable
data class Customer(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String
)

object Customers : Table() {
    val id = integer(name = "id").autoIncrement()
    val firstName = varchar(name = "firstName", length = 128)
    val lastName = varchar(name = "lastName", length = 128)
    val email = varchar(name = "email", length = 255)

    override val primaryKey = PrimaryKey(id)
}

val customers = mutableListOf<Customer>()