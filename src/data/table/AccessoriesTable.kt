package com.example.data.table

import org.jetbrains.exposed.sql.Table

object AccessoriesTable : Table()
{
     val Access_id  = integer("Access_id").autoIncrement()
     val Product_id = reference("Product_id",ProductTable.Product_id)
     val Specification = varchar("specification",40)
     val Price = float("Price")
    val quentity = integer("Quentity")

    override val primaryKey: PrimaryKey = PrimaryKey(Access_id)

}