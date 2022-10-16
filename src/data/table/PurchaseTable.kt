package com.example.data.table

import org.jetbrains.exposed.sql.Table

object PurchaseTable : Table()
{
    val Purchase_id = integer("Purchase_id")
    val Product_id = reference("Product_id",ProductTable.Product_id)
    val Supplier_id = reference("Supplier_id",SupplierTable.Supplier_id)
    val Date = varchar("Date",30)
    val Quantity = integer("Quantity")
    val Price = float("Price")

    override val primaryKey: PrimaryKey = PrimaryKey(Purchase_id)
}