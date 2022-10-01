package com.example.data.table

import org.jetbrains.exposed.sql.Table

object Product_varient : Table()
{
    val pv_id = varchar("pv_id",10)
    val product_id = varchar("product_id",10).references(Product.product_id)
    val color_id = varchar("color_id",10).references(Color.color_id)
    val ram = varchar("ram",10)
    val storage = varchar("storage",50)

    override val primaryKey: PrimaryKey = PrimaryKey(pv_id)
}