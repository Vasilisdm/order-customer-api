package com.example.dao

import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.*
import kotlinx.coroutines.Dispatchers

object DatabaseFactory {
    fun init(config: ApplicationConfig) {
        val jdbcURL = config.property(path = "storage.jdbcURL").getString()
        val driverClassName = config.property(path = "storage.driverClassName").getString()
        val dataSource = createDataSource(driver = driverClassName, url = jdbcURL)

        dataSource.connection.use { conn ->
            conn.createStatement().use { stmt ->
                stmt.executeQuery("SELECT 1")
            }
        }
    }

    private fun createDataSource(driver: String, url: String) = HikariDataSource().apply {
        jdbcUrl = url
        driverClassName = driver
    }
}