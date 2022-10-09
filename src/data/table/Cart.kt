package com.example.data.table

import org.jetbrains.exposed.sql.Table

object Cart : Table()
{
    val Cart_id = varchar("Cart_id",10)
    val Cust_id = varchar("Cust_id",10).references(Customer.Cust_id)
    val Product_id = varchar("Product_id",10).references(Product.Product_id)
}