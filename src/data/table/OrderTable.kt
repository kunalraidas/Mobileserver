package com.example.data.table

import org.jetbrains.exposed.sql.Table

object OrderTable : Table()
{
    val Order_id = varchar("order_id",10)
    val Cust_id =  varchar("cust_id",10).references(CustomerTable.Cust_id)
    val pv_id = varchar("pv_id",10).references(MobileTable.imei_number)


    override val primaryKey: PrimaryKey = PrimaryKey(Order_id)
}