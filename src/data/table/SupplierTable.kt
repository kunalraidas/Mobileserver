package com.example.data.table

import org.jetbrains.exposed.sql.Table

object SupplierTable : Table() {

    val Supplier_id = varchar("supplier_id",10)
    val Supplier_name = varchar("supplier_name",50)
    val Company_name = varchar("company_name",50)
    val Address = varchar("address",100)
    val Contact_no = varchar("contact_no",10)
    val Email_id = varchar("Email_id",50)

    override val primaryKey: PrimaryKey = PrimaryKey(Supplier_id)
}