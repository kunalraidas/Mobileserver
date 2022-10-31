package com.example.data.table

import org.jetbrains.exposed.sql.Table

object OrderTable : Table()
{
    val Order_id = integer("order_id")
    val Cart_id = reference("Cart_id",CartTable.Cart_id)
    val Date = varchar("Date",30)
    val Order_Status = varchar("Order_status",30)


    override val primaryKey: PrimaryKey = PrimaryKey(Order_id)
}