package com.example.data.table

import org.jetbrains.exposed.sql.Table

object AccessoriesTable : Table()
{
   val Access_id  = integer("Access_id")
   val Product_id = reference("Product_id",ProductTable.Product_id)
   val Color_id = reference("Color_id",ColorTable.Color_id)
    val Price = float("Price")

    override val primaryKey: PrimaryKey = PrimaryKey(Access_id)
 }