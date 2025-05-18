package com.mygamingstore.controllers

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

object AppController {

    // Endpoint para verificação do status da API
    suspend fun healthCheck(call: ApplicationCall) {
        call.respond(HttpStatusCode.OK, mapOf("status" to "GamingStore API está funcionando!"))
    }
    suspend fun getUserSession(call: ApplicationCall) {

        call.respond(mapOf("screen" to "home"))
    }

    suspend fun loginUser(call: ApplicationCall) {
        val request = call.receive<Map<String, String>>()
        val email = request["email"] ?: ""
        val password = request["password"] ?: ""

        if (email == "ryan@email.com" && password == "123456") {
            call.respond(mapOf("token" to "TOKEN_JWT_EXEMPLO"))
        } else {
            call.respond(mapOf("error" to "Credenciais inválidas"))
        }
    }

    suspend fun registerUser(call: ApplicationCall) {
        val newUser = call.receive<Map<String, String>>()
        //
        call.respond(mapOf("status" to "success"))
    }


}