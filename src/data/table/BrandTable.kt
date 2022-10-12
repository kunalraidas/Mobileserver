package com.example.data.table

import org.jetbrains.exposed.sql.Table

object BrandTable : Table()
{
   val Brand_id = varchar("brand_id",10)
    val  Brand_name = varchar("brand_name",50)

    override val primaryKey: PrimaryKey = PrimaryKey(Brand_id)
}