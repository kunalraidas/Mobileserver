package com.example.data.table

import org.jetbrains.exposed.sql.Table

object Product : Table()
{
    val product_id = varchar("product_id",10)
    val product_name = varchar("product_name",50)
    val product_desc = varchar("product_desc",100)
    val brand_id = varchar("brand_",10).references(Brand.brand_id)
    val cate_id = varchar("cate_id",10).references(Category.cate_id)

    override val primaryKey: PrimaryKey = PrimaryKey(product_id)
}