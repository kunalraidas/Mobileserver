package com.example.data.table

import org.jetbrains.exposed.sql.Table

object Product : Table()
{
    val Product_id = varchar("product_id",10)
    val Product_name = varchar("product_name",50)
    val Product_desc = varchar("product_desc",100)
    val Brand_id = varchar("brand_",10).references(Brand.Brand_id)
    val Cate_id = varchar("cate_id",10).references(Category.Cate_id)

    override val primaryKey: PrimaryKey = PrimaryKey(Product_id)
}