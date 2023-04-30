package com.example.dao

import com.example.models.CustomerCreated
import io.ktor.server.config.*
import nu.studer.sample.Tables.CUSTOMERS
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import java.sql.DriverManager

object DatabaseFactory {
    fun init(config: ApplicationConfig) {
        val jdbcURL = config.property(path = "storage.jdbcURL").getString()

        val connection = DriverManager.getConnection(jdbcURL)
        val create = DSL.using(connection, SQLDialect.H2)
        val customer = create
            .select(CUSTOMERS)
            .from(CUSTOMERS)
            .where(CUSTOMERS.ID.eq(1.toLong()))
            .fetchInto(CustomerCreated::class.java)

        println(customer)

    }
}