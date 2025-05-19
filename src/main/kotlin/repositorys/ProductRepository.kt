package com.mygamingstore.repositorys

import com.mygamingstore.database.ProductsSchema
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.util.*


data class Product(
    val id: String,
    val name: String,
    val description: String,
    val imageUri: String,
    val price: Double,
    val stock: Int,
    var isFavorite: Boolean = false,
    val isOnPromotion: Boolean,
    val promotionPrice: Double?,
    val brand: String,
    val available: Boolean
)

object ProductRepository {

    fun getAllProducts(): List<Product> = transaction {
        ProductsSchema.selectAll().map { row ->
            Product(
                id = row[ProductsSchema.id].toString(),
                name = row[ProductsSchema.name],
                description = row[ProductsSchema.description],
                imageUri = row[ProductsSchema.imageUri],
                price = row[ProductsSchema.price],
                stock = row[ProductsSchema.stock],
                isOnPromotion = row[ProductsSchema.isOnPromotion],
                promotionPrice = row[ProductsSchema.promotionPrice],
                isFavorite = row[ProductsSchema.isFavorite],
                brand = row[ProductsSchema.brand],
                available = row[ProductsSchema.available]
            )
        }
    }

    fun getProductById(id: String): Product? = transaction {
        ProductsSchema.select(ProductsSchema.id eq UUID.fromString(id))
            .mapNotNull { row ->
                Product(
                    id = row[ProductsSchema.id].toString(),
                    name = row[ProductsSchema.name],
                    description = row[ProductsSchema.description],
                    imageUri = row[ProductsSchema.imageUri],
                    price = row[ProductsSchema.price],
                    stock = row[ProductsSchema.stock],
                    isOnPromotion = row[ProductsSchema.isOnPromotion],
                    promotionPrice = row[ProductsSchema.promotionPrice],
                    isFavorite = row[ProductsSchema.isFavorite],
                    brand = row[ProductsSchema.brand],
                    available = row[ProductsSchema.available]
                )
            }.singleOrNull()
    }

    fun createProduct(product: Product) = transaction {
        ProductsSchema.insert { row ->
            row[id] = UUID.fromString(product.id)
            row[name] = product.name
            row[description] = product.description
            row[imageUri] = product.imageUri
            row[price] = product.price
            row[stock] = product.stock
            row[isOnPromotion] = product.isOnPromotion
            row[promotionPrice] = product.promotionPrice
            row [isFavorite] = product.isFavorite
            row[brand] = product.brand
            row[available] = product.available
        }
    }

    fun updateProduct(id: String, product: Product) = transaction {
        ProductsSchema.update({ ProductsSchema.id eq UUID.fromString(id) }) { row ->
            row[name] = product.name
            row[description] = product.description
            row[imageUri] = product.imageUri
            row[price] = product.price
            row[stock] = product.stock
            row[isOnPromotion] = product.isOnPromotion
            row[promotionPrice] = product.promotionPrice
            row [isFavorite] = product.isFavorite
            row[brand] = product.brand
            row[available] = product.available
        }
    }

    fun deleteProduct(id: String) = transaction {
        ProductsSchema.deleteWhere { ProductsSchema.id eq UUID.fromString(id) }
    }
}

