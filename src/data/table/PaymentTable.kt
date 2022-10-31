package com.example.data.table

import org.jetbrains.exposed.sql.Table

object PaymentTable : Table()
{
    val Payment_id =  varchar("payment_id",10)
    val Email = reference("Email",CustomerTable.Email)
    val Order_id = reference("Order_id",OrderTable.Order_id)
    val Payment_method = varchar("payment_method",30)
    val Payment_status = varchar("payment_status",30)

    override val primaryKey: PrimaryKey = PrimaryKey(Payment_id)
}