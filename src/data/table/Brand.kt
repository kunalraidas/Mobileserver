package com.example.data.table

import org.jetbrains.exposed.sql.Table

object Brand : Table()
{
   val brand_id = varchar("brand_id",10)
    val  brand_name = varchar("brand_name",50)

    override val primaryKey: PrimaryKey = PrimaryKey(brand_id)
}