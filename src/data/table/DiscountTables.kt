package com.example.data.table

import org.jetbrains.exposed.sql.Table

object DiscountTables : Table()
{
    val Coupon_code = integer("discount_id")
    val Discount_perc = varchar("discount_perc",20)
    val Total_Coupon = integer("Total_coupon")


    override val primaryKey: PrimaryKey = PrimaryKey(Coupon_code)
}