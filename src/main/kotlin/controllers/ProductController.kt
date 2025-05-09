package com.mygamingstore.controllers

import com.mygamingstore.repositorys.Product
import com.mygamingstore.repositorys.ProductRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

object ProductController {
    // GET /products
    suspend fun getProducts(call: ApplicationCall) {
        try {
            val products = ProductRepository.getAllProducts()
            call.respond(products) // O ContentNegotiation converte para JSON
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, "Erro ao recuperar produtos: ${e.localizedMessage}")
        }
    }
    suspend fun getProductById(call: ApplicationCall) {
        try {
            val id = call.parameters["id"]
                ?: return call.respond(HttpStatusCode.BadRequest, "Parâmetro 'id' não informado")

            val product = ProductRepository.getProductById(id)

            if (product == null) {
                call.respond(HttpStatusCode.NotFound, "Produto com id $id não encontrado")
            } else {
                call.respond(product)
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, "Erro ao buscar o produto: ${e.localizedMessage}")
        }
    }



    // POST /products
    suspend fun createProduct(call: ApplicationCall) {
        try {
            // Recebe o produto no corpo da requisição
            val product = call.receive<Product>()
            // Gera um ID se necessário
            val newProduct = if (product.id.isBlank()) {
                product.copy(id = UUID.randomUUID().toString())
            } else product

            ProductRepository.createProduct(newProduct)
            call.respond(HttpStatusCode.Created, newProduct)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, "Erro ao criar produto: ${e.localizedMessage}")
        }
    }

    // PUT /products/{id}
    suspend fun updateProduct(call: ApplicationCall) {
        try {
            val id = call.parameters["id"] ?: return call.respond(HttpStatusCode.BadRequest, "Parâmetro 'id' não informado")
            val product = call.receive<Product>()
            ProductRepository.updateProduct(id, product)
            call.respond(HttpStatusCode.OK, "Produto atualizado com sucesso!")
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, "Erro ao atualizar produto: ${e.localizedMessage}")
        }
    }

    // DELETE /products/{id}
    suspend fun deleteProduct(call: ApplicationCall) {
        try {
            val id = call.parameters["id"] ?: return call.respond(HttpStatusCode.BadRequest, "Parâmetro 'id' não informado")
            ProductRepository.deleteProduct(id)
            call.respond(HttpStatusCode.OK, "Produto deletado com sucesso!")
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, "Erro ao deletar produto: ${e.localizedMessage}")
        }
    }
}