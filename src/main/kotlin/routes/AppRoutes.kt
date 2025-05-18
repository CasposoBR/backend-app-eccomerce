package com.mygamingstore.routes

import com.mygamingstore.controllers.AppController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.appRoutes() {
    route("/app") {
        get("/health") {
            AppController.healthCheck(call)
        }


        get("/session") {
            AppController.getUserSession(call)
        }


        post("/login") {
            AppController.loginUser(call)
        }


        post("/register") {
            AppController.registerUser(call)
        }
    }
}