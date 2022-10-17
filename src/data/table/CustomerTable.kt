package com.example.data.table

import org.jetbrains.exposed.sql.Table

object CustomerTable : Table()
{
    val Email = varchar("Email",50)
    val Password = varchar("Password",100)
    val First_name = varchar("First_name",30)
    val Last_name = varchar("Last_name",30)
    val Phone_no = long("Phone_no")
    val Cust_Address = varchar("Cust_Address",100)
    val Delivery_Address = varchar("Delivery_Address",100)
    val Pincode = integer("Pincode")


    override val primaryKey: PrimaryKey = PrimaryKey(Email)
}