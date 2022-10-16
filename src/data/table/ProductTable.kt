package com.example.data.table

import org.jetbrains.exposed.sql.Table

object ProductTable : Table()
{
    val Product_id = integer("Product_id")
    val Product_name = varchar("Product_name",30)
    val Product_desc = varchar("Product_desc",100)
    val Brand_name = varchar("Brand_name",30)
    val Cate_name = varchar("Cate_name",30)

    override val primaryKey: PrimaryKey = PrimaryKey(Product_id)
}