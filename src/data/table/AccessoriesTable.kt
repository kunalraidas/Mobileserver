package com.example.data.table

import org.jetbrains.exposed.sql.Table

object AccessoriesTable : Table() {
    val Access_id  = varchar("access_id",10)
    val Product_id = varchar("product_id",10).references(ProductTable.Product_id)
    // val  Price = varchar("price",)


    override val primaryKey: PrimaryKey = PrimaryKey(Access_id)
 }