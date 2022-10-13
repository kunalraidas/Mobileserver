package com.example.data.table

import org.jetbrains.exposed.sql.Table

object InvoiceTable : Table()
{
        val inv_id = integer("inv_id")
        val Cust_id = reference("cust_id",CustomerTable.Cust_id)
        val invoice_details = varchar("invoice_details",100)



    override val primaryKey: PrimaryKey = PrimaryKey(inv_id)
}