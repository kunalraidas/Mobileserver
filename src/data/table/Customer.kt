package com.example.data.table

import org.jetbrains.exposed.sql.Table

object Customer : Table()
{
    val Cust_id = varchar("cust_id",10)
    val Cust_name = varchar("cust_name",50)
    val Cust_address = varchar("cust_address",100)
    val Cust_email = varchar("cust_email",50)
    val Cust_password = varchar("cust_password",10)
    val Cust_phone = varchar("cust_phone",10)

    override val primaryKey: PrimaryKey = PrimaryKey(Cust_id)
}