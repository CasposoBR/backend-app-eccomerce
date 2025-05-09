package com.mygamingstore.database

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabases() {
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/gamingstore", // altere para sua URL
        driver = "org.postgresql.Driver",
        user = "seu_usuario",
        password = "sua_senha"
    )
}
