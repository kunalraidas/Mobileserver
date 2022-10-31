package com.example.data.table

import org.jetbrains.exposed.sql.Table

object BrandTable : Table()
{
    val brand_id  = integer("brand_id")
    val brand_name = varchar("brand_name",30)

    override val primaryKey: PrimaryKey = PrimaryKey(brand_id)
}