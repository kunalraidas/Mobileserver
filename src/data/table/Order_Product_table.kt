package com.example.data.table



import org.jetbrains.exposed.sql.Table

object Order_Product_table : Table() {
    val order_id = reference("order_id",OrderTable.order_id)
    val product_id = reference("product_id",ProductTable.Product_id)
    // Total price = mobile_id/access_id price * quantity
    val total_price = float("total_price")
    val quentity = integer("quantity")
    val mobile_id = reference("Mobile_id",MobileTable.Mobile_id).nullable()
    val access_id = reference("Access_id",AccessoriesTable.Access_id).nullable()
    val color_id = reference("color_id",ColorTable.Color_id).nullable()
}