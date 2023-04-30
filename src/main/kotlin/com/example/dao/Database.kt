package com.example.dao

import com.example.configuration.DatabaseConfig
import com.sksamuel.hoplite.ConfigLoaderBuilder
import com.sksamuel.hoplite.addResourceSource
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import java.sql.DriverManager

object Database {
    fun getConnection(): DSLContext {
        val dbConfig = ConfigLoaderBuilder.default()
            .addResourceSource(resource = "/database.conf")
            .build()
            .loadConfigOrThrow<DatabaseConfig>()

        val connection = DriverManager.getConnection(dbConfig.jdbcURL)
        return DSL.using(connection, SQLDialect.H2)
    }
}