package com.example.data.table

import org.jetbrains.exposed.sql.Table

object MobileTable : Table()
{
    val imei_number = varchar("imei_number",10)
    val Product_id = reference("product_id",ProductTable.Product_id)
    val Color_id = reference("color_id",ColorTable.Color_id)
    val Ram = varchar("ram",10)
    val Storage = varchar("storage",50)
//    val Price =

    override val primaryKey: PrimaryKey = PrimaryKey(imei_number)
}