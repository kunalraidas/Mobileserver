package com.example.data.table

import org.jetbrains.exposed.sql.Table

object Stock : Table()
{
    val Stock_id = varchar("stock_id",10)
    val pv_id = varchar("pv_id",10).references(MobileTable.imei_number)
    val total_stock = varchar("total stock",50)

    override val primaryKey: PrimaryKey = PrimaryKey(Stock_id)
}