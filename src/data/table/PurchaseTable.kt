package com.example.data.table

import org.jetbrains.exposed.sql.Table

object PurchaseTable : Table()
{
    val Purchase_id = integer("Purchase_id")
    val Product_id =  reference("Product_id",ProductTable.Product_id)
    val Supplier_email = reference("Supplier_email",SupplierTable.Supplier_email)
    val Date = varchar("Date",30)
    val Quantity = integer("Quantity")
    // Count each product price with base price
    val Price = float("Price")

    override val primaryKey: PrimaryKey = PrimaryKey(Purchase_id)
}