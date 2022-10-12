package com.example.data.table

import org.jetbrains.exposed.sql.Table

object InvoiceTable : Table()
{
        val inv_id = varchar("inv_id",10)


    override val primaryKey: PrimaryKey = PrimaryKey(inv_id)
}