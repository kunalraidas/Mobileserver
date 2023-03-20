package com.example.data.table

import org.jetbrains.exposed.sql.Table

object MobileTable : Table()
{
    val Mobile_id = integer("Mobile_id")
    val Product_id = reference("Product_id",ProductTable.Product_id)
//    val Color_id = reference("Color_id",ColorTable.Color_id)
    val Ram = varchar("Ram",30)
    val Storage = varchar("Storage",30)
     val Price = float("Price")

    override val primaryKey: PrimaryKey = PrimaryKey(Mobile_id)

}