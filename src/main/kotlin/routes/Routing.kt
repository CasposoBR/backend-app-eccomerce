package com.mygamingstore.routes

import com.mygamingstore.controllers.AppController
import com.mygamingstore.controllers.ProductController
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(
                text = "Erro Interno: ${cause.localizedMessage}",
                status = HttpStatusCode.InternalServerError
            )
        }
    }

    routing {
        appRoutes()
        // Rota pública de boas-vindas
        get("/") {
            call.respondText("Bem-vindo ao GamingStore!")
        }

        // Rota para health check e monitoramento
        route("/app") {
            get("/health") {
                AppController.healthCheck(call)
            }

            get("/session") {  // 🔹 Agora define a rota corretamente!
                val userId = call.request.queryParameters["userId"]
                if (userId.isNullOrEmpty()) {
                    call.respond(HttpStatusCode.BadRequest, "userId não informado!")
                } else {
                    call.respond(HttpStatusCode.OK, mapOf("message" to "Sessão iniciada!", "userId" to userId))
                }
            }
        }

        // Rotas protegidas para manipulação de produtos
        authenticate("jwt-auth") { // Certifique-se de que o provider "jwt-auth" esteja configurado
            route("/products") {
                get { ProductController.getProducts(call) }
                get("/{id}") { ProductController.getProductById(call) }
                post { ProductController.createProduct(call) }
                put("/{id}") { ProductController.updateProduct(call) }
                delete("/{id}") { ProductController.deleteProduct(call) }
            }
        }

        // Conteúdo estático (opcional)
        static("/static") {
            resources("static")
        }
    }
}
