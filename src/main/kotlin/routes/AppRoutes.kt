package com.mygamingstore.routes

import com.mygamingstore.controllers.AppController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.appRoutes() {
    route("/app") {
        get("/health") {
            AppController.healthCheck(call)
        }
        // Outras rotas específicas do AppController podem ser adicionadas aqui
    }
}