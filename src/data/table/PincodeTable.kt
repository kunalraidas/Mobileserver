package com.example.data.table

import org.jetbrains.exposed.sql.Table

object PincodeTable : Table()
{
    val Pincode = integer("Pincode")
    val Area_name = varchar("Area_name",30)
   // val Delivery_Charge = integer("Delivery_Charge")

    override val primaryKey: PrimaryKey = PrimaryKey(Pincode)
}