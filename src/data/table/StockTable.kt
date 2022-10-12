package com.example.data.table

import org.jetbrains.exposed.sql.Table

object StockTable : Table()
{
    val Stock_id = varchar("stock_id",10)
    val Purchase_id = varchar("purchase_id",10)
    val Order_id = varchar("Order_id",10).references(OrderTable.Order_id)
    // val sold_out =
    // val Available Stock =
    // Total Stock =

    override val primaryKey: PrimaryKey = PrimaryKey(Stock_id)
}