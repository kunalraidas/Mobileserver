package com.example.data.table

import org.jetbrains.exposed.sql.Table

object InvoiceTable : Table()
{
        val Invoice_id = integer("inv_id")
        val Order_id = reference("Order_id",OrderTable.Order_id)
        val Date = varchar("Date",30)
        val Discount = integer("Discount")
        val Delivery_Charge = integer("Delivery_Charge")
        val Invoice_Details = varchar("Invoice_Detail",100)

    override val primaryKey: PrimaryKey = PrimaryKey(Invoice_id)
}