package com.example.data.table

import org.jetbrains.exposed.sql.Table

object MobileTable : Table()
{
    val imei_number = varchar("imei_number",10)
    val Product_id = varchar("product_id",10).references(Product.Product_id)
    val Color_id = varchar("color_id",10).references(ColorTable.Color_id)
    val Ram = varchar("ram",10)
    val Storage = varchar("storage",50)

    override val primaryKey: PrimaryKey = PrimaryKey(imei_number)
}