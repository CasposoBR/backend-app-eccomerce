package com.mygamingstore.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.util.*

object UsersTable : Table("users") {
    val id = uuid("id").default(UUID.randomUUID())
    val name = varchar("name", 255)
    val email = varchar("email", 255).uniqueIndex()
    val passwordHash = varchar("password_hash", 255) //
}

