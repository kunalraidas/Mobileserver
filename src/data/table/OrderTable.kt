package com.example.data.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.`java-time`.date


object OrderTable : Table()
{
     val order_id = varchar("order_id",50)
     val email = reference("email",CustomerTable.Email)
     val order_date = date("order_date")
     val trackingUrl = varchar("tracking_url",128).nullable()
     val orderStatus = integer("order_status")
     val paymentStatus = integer("payment_status")
     val total = float("total")
     val discount = float("discount")
      val deliveryCharge = float("delivery_Charge")
     val total_received = float("total_received")


    override val primaryKey: PrimaryKey = PrimaryKey(order_id)
}
