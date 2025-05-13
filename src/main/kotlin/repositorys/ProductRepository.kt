package com.mygamingstore.repositorys

import com.mygamingstore.database.ProductsSchema
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update


data class Product(
    val id: String,
    val name: String,
    val description: String,
    val imageUri: String,
    val price: Double,
    val stock: Int,
    val isOnPromotion: Boolean,
    val promotionPrice: Double?,
    val brand: String,
    val available: Boolean
)

object ProductRepository {

    fun getAllProducts(): List<Product> = transaction {
        ProductsSchema.selectAll().map { row ->
            Product(
                id = row[ProductsSchema.id],
                name = row[ProductsSchema.name],
                description = row[ProductsSchema.description],
                imageUri = row[ProductsSchema.imageUri],
                price = row[ProductsSchema.price],
                stock = row[ProductsSchema.stock],
                isOnPromotion = row[ProductsSchema.isOnPromotion],
                promotionPrice = row[ProductsSchema.promotionPrice],
                brand = row[ProductsSchema.brand],
                available = row[ProductsSchema.available]
            )
        }
    }

    fun getProductById(id: String): Product? = transaction {
        ProductsSchema.select(ProductsSchema.id eq id)
            .mapNotNull { row ->
                Product(
                    id = row[ProductsSchema.id],
                    name = row[ProductsSchema.name],
                    description = row[ProductsSchema.description],
                    imageUri = row[ProductsSchema.imageUri],
                    price = row[ProductsSchema.price],
                    stock = row[ProductsSchema.stock],
                    isOnPromotion = row[ProductsSchema.isOnPromotion],
                    promotionPrice = row[ProductsSchema.promotionPrice],
                    brand = row[ProductsSchema.brand],
                    available = row[ProductsSchema.available]
                )
            }.singleOrNull()
    }

    fun createProduct(product: Product) = transaction {
        ProductsSchema.insert { row ->
            row[id] = product.id  // se estiver usando UUID, gere com UUID.randomUUID().toString()
            row[name] = product.name
            row[description] = product.description
            row[imageUri] = product.imageUri
            row[price] = product.price
            row[stock] = product.stock
            row[isOnPromotion] = product.isOnPromotion
            row[promotionPrice] = product.promotionPrice
            row[brand] = product.brand
            row[available] = product.available
        }
    }

    fun updateProduct(id: String, product: Product) = transaction {
        ProductsSchema.update({ ProductsSchema.id eq id }) { row ->
            row[name] = product.name
            row[description] = product.description
            row[imageUri] = product.imageUri
            row[price] = product.price
            row[stock] = product.stock
            row[isOnPromotion] = product.isOnPromotion
            row[promotionPrice] = product.promotionPrice
            row[brand] = product.brand
            row[available] = product.available
        }
    }

    fun deleteProduct(id: String) = transaction {
        ProductsSchema.deleteWhere { ProductsSchema.id eq id }
    }
}

