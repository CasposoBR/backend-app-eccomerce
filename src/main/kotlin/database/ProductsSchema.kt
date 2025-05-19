package com.mygamingstore.database

import org.jetbrains.exposed.sql.Table
import java.util.*

object ProductsSchema : Table("products") {

    val id = uuid("id").default(UUID.randomUUID())
    val name = varchar("name", 255)
    val description = text("description")
    val imageUri = varchar("image_uri", 512)
    val price = double("price")
    val stock = integer("stock")
    val isFavorite = bool("is_favorite").default(false)
    val isOnPromotion = bool("is_on_promotion")
    val promotionPrice = double("promotion_price").nullable()
    val brand = varchar("brand", 255)
    val available = bool("available")

    override val primaryKey = PrimaryKey(id, name = "PK_Products_ID")
}


