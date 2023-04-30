package com.example

//import com.example.dao.DatabaseFactory
import com.example.dao.DatabaseFactory
import io.ktor.server.application.*
import io.ktor.server.netty.*
import com.example.plugins.*

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module() {
    DatabaseFactory.init(environment.config)
//    configureRouting()
//    configureSerialization()
}