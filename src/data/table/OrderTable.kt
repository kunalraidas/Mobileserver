package com.example.data.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.`java-time`.date


object OrderTable : Table()
{

    val Order_id = integer("order_id")
    val Email = reference("Email",CustomerTable.Email)
    val Cart_id = reference("Cart_id",CartTable.Cart_id)
    val Order_date = date("Order_date")
    val Order_Status = varchar("Order_status",30)


    override val primaryKey: PrimaryKey = PrimaryKey(Order_id)
}