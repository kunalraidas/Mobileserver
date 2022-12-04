package com.example.data.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.`java-time`.date


object OrderTable : Table()
{

    val Order_id = integer("order_id")
    val Email = reference("Email",CustomerTable.Email)
    val Cart_id = reference("Cart_id",CartTable.Cart_id)
    val Order_date = date("Order_date")
    val Order_Status = varchar("Order_status",30)
    val Date = varchar("Date",30)
    val trackingUrl = varchar("tracking_url",128).nullable()
    val orderStatus = integer("order_status")
    val paymentStatus = integer("payment_status")
    val total = float("total")
    val discount = float("discount")
    val deliveryCharge = float("delivery_Charge")
    val Totalrecived = float("Total_recived")


    override val primaryKey: PrimaryKey = PrimaryKey(Order_id)
}