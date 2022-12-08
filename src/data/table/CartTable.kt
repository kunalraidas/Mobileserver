package com.example.data.table

import org.jetbrains.exposed.sql.Table

object CartTable : Table()
{
    val Cart_id = integer("Cart_id").autoIncrement()
    val Email = reference("Cust_Email",CustomerTable.Email)
    val Product_id =  reference("Product_id",ProductTable.Product_id)
    val Quentity = integer("quentity")
    val TotalPrice = float("totalPrice")


    override val primaryKey: PrimaryKey = PrimaryKey(Cart_id)

}