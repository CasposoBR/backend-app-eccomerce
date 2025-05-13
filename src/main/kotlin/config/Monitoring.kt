package com.mygamingstore.config

import io.ktor.server.application.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.request.*
import org.slf4j.event.Level

fun Application.configureMonitoring() {
    install(CallLogging) {
        level = Level.INFO // 🔹 Ajuste para usar a classe correta
        filter { call ->
            call.request.path().startsWith("/")
        }
    }
}
