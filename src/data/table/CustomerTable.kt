package com.example.data.table

import org.jetbrains.exposed.sql.Table

object CustomerTable : Table()
{
    val Cust_id = integer("cust_id").autoIncrement()
    val Cust_name = varchar("cust_name",50)
    val Cust_address = varchar("cust_address",100)
    val Address_pincode = varchar("address_pincode",50)
    val Cust_email = varchar("cust_email",50)
    val Cust_password = varchar("cust_password",10)
    val Cust_phone = varchar("cust_phone",10)

    override val primaryKey: PrimaryKey = PrimaryKey(Cust_id)
}