package com.example.data.table

import org.jetbrains.exposed.sql.Table

object PurchaseTable : Table()
{
    val Purchase_id = integer("Purchase_id")
    val Mobile_id = reference("mobile_id",MobileTable.Mobile_id)
    val Access_id = reference("access_id",AccessoriesTable.Access_id)
    val Supplier_email = reference("Supplier_email",SupplierTable.Supplier_email)
    val Date = varchar("Date",30)
    val Quantity = integer("Quantity")
    val Price = float("Price")

    override val primaryKey: PrimaryKey = PrimaryKey(Purchase_id)
}