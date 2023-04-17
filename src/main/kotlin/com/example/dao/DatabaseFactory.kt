package com.example.dao

import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.*
import org.flywaydb.core.Flyway
import javax.sql.DataSource

object DatabaseFactory {
    fun init(config: ApplicationConfig) {
        val jdbcURL = config.property(path = "storage.jdbcURL").getString()
        val driverClassName = config.property(path = "storage.driverClassName").getString()
        val dataSource = createAndMigrateDatasource(driver = driverClassName, url = jdbcURL)

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

    private fun migrateDataSource(dataSource: DataSource) {
        Flyway.configure()
            .dataSource(dataSource)
            .locations("db/migration")
            .table("flyway_schema_history")
            .load()
            .migrate()
    }

    private fun createAndMigrateDatasource(driver: String, url: String) =
        createDataSource(driver, url).also { migrateDataSource(it) }
}