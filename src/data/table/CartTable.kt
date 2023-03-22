package com.example.data.table

import com.example.data.model.Mobile
import org.jetbrains.exposed.sql.Table

object CartTable : Table()
{
    val Cart_id = integer("Cart_id").autoIncrement()
    val Email = reference("Cust_Email",CustomerTable.Email)
    val Product_id =  reference("Product_id",ProductTable.Product_id)
    val Quentity = integer("quentity")
    val TotalPrice = float("totalPrice")
    val mobile_id = reference("Mobile_id",MobileTable.Mobile_id).nullable()
    val access_id = reference("Access_id",AccessoriesTable.Access_id).nullable()
    val color_id = reference("color_id",ColorTable.Color_id).nullable()



    override val primaryKey: PrimaryKey = PrimaryKey(Cart_id)

}