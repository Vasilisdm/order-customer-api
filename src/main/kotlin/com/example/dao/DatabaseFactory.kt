package com.example.dao

import com.example.models.CustomersTable
import com.example.models.OrdersTable
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        val jdbcURL = "jdbc:h2:file:./build/customerdb"
        val driverClassName = "org.h2.Driver"
        val database = Database.connect(url = jdbcURL, driver = driverClassName)
        transaction(database) {
            SchemaUtils.create(CustomersTable)
            SchemaUtils.create(OrdersTable)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}