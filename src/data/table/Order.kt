package com.example.data.table

import org.jetbrains.exposed.sql.Table

object Order : Table()
{
    val Order_id = varchar("order_id",10)
    val Product_id = varchar("product_id",10).references(Product.Product_id)

    override val primaryKey: PrimaryKey = PrimaryKey(Order_id)
}