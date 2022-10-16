package com.example.data.table

import org.jetbrains.exposed.sql.Table

object CartTable : Table()
{
    val Cart_id = integer("Cart_id")
    val Email = reference("Cust_id",CustomerTable.Email)
    val Mobile_id = reference("Mobile_id",MobileTable.Mobile_id)
    val Access_id = reference("Access_id",AccessoriesTable.Access_id)
    val Quantity = integer("Quantity")
     val Total = float("Total")

    override val primaryKey: PrimaryKey = PrimaryKey(Cart_id)

}