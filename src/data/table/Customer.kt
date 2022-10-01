package com.example.data.table

import org.jetbrains.exposed.sql.Table

object Customer : Table()
{
    val cust_id = varchar("cust_id",10)
    val cust_name = varchar("cust_name",50)
    val cust_address = varchar("cust_address",100)
    val cust_email = varchar("cust_email",50)
    val cust_password = varchar("cust_password",10)
    val cust_phone = varchar("cust_phone",10)

    override val primaryKey: PrimaryKey = PrimaryKey(cust_id)
}