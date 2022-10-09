package com.example.data.table

import org.jetbrains.exposed.sql.Table

object Stock : Table()
{
    val Stock_id = varchar("stock_id",10)
    val Product_id = varchar("product_id",10).references(Product.Product_id)
    val total_stock = varchar("total stock",50)
}