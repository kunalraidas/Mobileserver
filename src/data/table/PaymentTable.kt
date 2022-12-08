package com.example.data.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.`java-time`.date

object PaymentTable : Table()
{
    val payment_id =  varchar("payment_id",32)
    val email = reference("Email",CustomerTable.Email)
    val order_id = reference("Order_id",OrderTable.order_id)
    val date = date("payment_date")
    val Payment_method = integer("payment_method")
    val delivery_charge = float("delivery_charge")
    val amount = float("amount")

    override val primaryKey: PrimaryKey = PrimaryKey(payment_id)
}