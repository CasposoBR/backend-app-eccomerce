package com.mygamingstore.database

import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun createTables() {
    transaction {
        SchemaUtils.create(ProductsSchema)
        // Adicione aqui outras tabelas, por exemplo, a tabela de usuários em UsersSchema
    }
}

