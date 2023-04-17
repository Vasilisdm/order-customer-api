package com.example.dao

import com.example.models.CustomersTable
import com.example.models.OrderItemsTable
import com.example.models.OrdersTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init(config: ApplicationConfig) {
        val jdbcURL = config.property(path = "storage.jdbcURL").getString()
        val driverClassName = config.property(path = "storage.driverClassName").getString()
        val database = Database.connect(createHikariDataSource(driver = driverClassName, url = jdbcURL))
        transaction(database) {
            SchemaUtils.create(CustomersTable)
            SchemaUtils.create(OrdersTable)
            SchemaUtils.create(OrderItemsTable)
            addLogger(StdOutSqlLogger)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    private fun createHikariDataSource(driver: String, url: String) = HikariDataSource(HikariConfig().apply {
        driverClassName = driver
        jdbcUrl = url
        maximumPoolSize = 3
        isAutoCommit = false
        transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        validate()
    })
}