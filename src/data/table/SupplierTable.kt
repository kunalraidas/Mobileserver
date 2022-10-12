package com.example.data.table

import org.jetbrains.exposed.sql.Table

object SupplierTable : Table() {

    val supplier_id = varchar("supplier_id",10)
    val company_name = varchar("company_name",50)
    val supplier_name = varchar("supplier_name",50)
    val address = varchar("address",100)
    val contact_no = varchar("contact_no",10)
    val Email_id = varchar("Email_id",50)

    override val primaryKey: PrimaryKey = PrimaryKey(supplier_id)
}