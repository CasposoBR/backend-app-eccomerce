package com.mygamingstore.database

import org.jetbrains.exposed.sql.Table

object ProductsSchema : Table("products") {
    // Utilizando UUID pode ser interessante para garantir a unicidade, mas aqui usamos varchar para simplificar
    val id = varchar("id", 36)
    val name = varchar("name", 255)
    val description = text("description")
    val imageUri = varchar("image_uri", 512)
    val price = double("price")
    val stock = integer("stock")
    val isOnPromotion = bool("is_on_promotion")
    val promotionPrice = double("promotion_price").nullable()
    val brand = varchar("brand", 255)
    val available = bool("available")

    override val primaryKey = PrimaryKey(id, name = "PK_Products_ID")
}


