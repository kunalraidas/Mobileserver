package com.example.data.table

import org.jetbrains.exposed.sql.Table

object DiscountTables : Table()
{
    val Discount_id = varchar("discount_id",10)
    val Discount_perc = varchar("discount_perc",20)


    override val primaryKey: PrimaryKey = PrimaryKey(Discount_id)
}