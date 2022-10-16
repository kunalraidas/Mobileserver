package com.example.data.table

import org.jetbrains.exposed.sql.Table

object StockTable : Table()
{
    val Stock_id = varchar("stock_id",10)
    val Purchase_id = reference("Purchase_id",PurchaseTable.Purchase_id)
    val Order_id = reference("Order_id",OrderTable.Order_id)
    val Sold_out = integer("Sold_out")
    val Available = integer("Available")
    val Total = integer("Total")

    override val primaryKey: PrimaryKey = PrimaryKey(Stock_id)
}