package com.example.data.table

import org.jetbrains.exposed.sql.Table

object BrandTable : Table()
{
   val Brand_id = integer("brand_id")
    val  Brand_name = varchar("brand_name",50)

    override val primaryKey: PrimaryKey = PrimaryKey(Brand_id)
}