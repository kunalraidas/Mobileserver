package com.example.data.table

import org.jetbrains.exposed.sql.Table

object PurchaseTable : Table()
{
    val Purchase_id = varchar("purchase_id",10)
    val Product_id = varchar("product_id",10).references(ProductTable.Product_id)
    val Supplier_id = varchar("supplier_id",10).references(SupplierTable.supplier_id)
    // val Date =
    // val Quantity =
    // Price =


    override val primaryKey: PrimaryKey = PrimaryKey(Purchase_id)
}