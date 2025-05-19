package com.mygamingstore.database

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases() {
    fun init() {
        Database.connect(
            url = "jdbc:postgresql://10.0.2.2:5432/gamingstore", // altere para sua URL
            driver = "org.postgresql.Driver",
            user = "admin",
            password = "password"
        )
        transaction {
            SchemaUtils.create(UsersTable, ProductsSchema)
        }
    }
}