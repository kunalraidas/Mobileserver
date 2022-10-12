package com.example.data.table

import org.jetbrains.exposed.sql.Table

object InvoiceTable : Table()
{
        val inv_id = varchar("inv_id",10)
        val Cust_id = varchar("cust_id",10).references(CustomerTable.Cust_id)
        val invoice_details = varchar("invoice_details",100)



    override val primaryKey: PrimaryKey = PrimaryKey(inv_id)
}