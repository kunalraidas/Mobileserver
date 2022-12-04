package com.example.data.table

import org.jetbrains.exposed.sql.Table

object ProductTable : Table()
{
    val Product_id = integer("Product_id")
    val Product_name = varchar("Product_name",50)
    val Product_desc = text("Product_desc")   //varchar("Product_desc",1000)
    val Brand_id = reference("brand_id",BrandTable.brand_id)
    val Cate_name = varchar("Cate_name",50)

    override val primaryKey: PrimaryKey = PrimaryKey(Product_id)
}

