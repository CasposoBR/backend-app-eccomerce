package com.mygamingstore

import com.mygamingstore.auth.configureSecurity
import com.mygamingstore.database.configureDatabases
import com.mygamingstore.config.configureHTTP
import com.mygamingstore.config.configureMonitoring
import com.mygamingstore.config.configureSerialization
import com.mygamingstore.routes.configureRouting
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSecurity()
    configureSerialization()
    configureDatabases()
    configureHTTP()
    configureMonitoring()
    configureRouting()
}