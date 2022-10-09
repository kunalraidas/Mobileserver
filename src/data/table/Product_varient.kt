package com.example.data.table

import org.jetbrains.exposed.sql.Table

object Product_varient : Table()
{
    val Pv_id = varchar("pv_id",10)
    val Product_id = varchar("product_id",10).references(Product.Product_id)
    val Color_id = varchar("color_id",10).references(Color.Color_id)
    val Ram = varchar("ram",10)
    val Storage = varchar("storage",50)

    override val primaryKey: PrimaryKey = PrimaryKey(Pv_id)
}