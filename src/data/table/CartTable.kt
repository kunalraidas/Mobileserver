package com.example.data.table

import org.jetbrains.exposed.sql.Table

object CartTable : Table()
{
    val Cart_id = integer("Cart_id")
    val Cust_id = varchar("Cust_id",10).references(CustomerTable.Cust_id)
    val IMEI_Number = varchar("IMEI_number",20).references(MobileTable.imei_number)
    val Access_id = varchar("access_id",10).references(AccessoriesTable.Access_id)

//    val Product_id = varchar("Product_id",10).references(Product.Product_id)

    override val primaryKey: PrimaryKey = PrimaryKey(Cart_id)
}