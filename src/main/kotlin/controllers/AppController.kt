package com.mygamingstore.controllers

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

object AppController {

    // Endpoint para verificação do status da API
    suspend fun healthCheck(call: ApplicationCall) {
        call.respond(HttpStatusCode.OK, mapOf("status" to "GamingStore API está funcionando!"))
    }

    // Você pode adicionar outros métodos que encapsulem a lógica de negócio relacionada à aplicação
    // Por exemplo, retornar informações como versão, data/hora do servidor, etc.
}