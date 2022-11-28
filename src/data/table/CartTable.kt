package com.example.data.table

import org.jetbrains.exposed.sql.Table

object CartTable : Table()
{
    val Cart_id = integer("Cart_id").autoIncrement()
    val Email = reference("Cust_id",CustomerTable.Email)
    val Product_id =  reference("Product_id",ProductTable.Product_id)
    val Quentity = integer("quentity")
    // Count Total Price base price of product * quentity
    val TotalPrice = float("totalPrice")
//   val Mobile_id = reference("Mobile_id",MobileTable.Mobile_id)
//    val Access_id = reference("Access_id",AccessoriesTable.Access_id)


    override val primaryKey: PrimaryKey = PrimaryKey(Cart_id)

}