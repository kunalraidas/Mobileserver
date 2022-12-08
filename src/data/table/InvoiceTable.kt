package com.example.data.table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.`java-time`.date

object InvoiceTable : Table()
{
        val Invoice_id = varchar("invoice_id",32)
        val date = date("invoice_date")
        val delivery_charge = float("delivery_charge")
        val payment_id = reference("payment_id",PaymentTable.payment_id)
        val email_id = reference("email_id",CustomerTable.Email)

    override val primaryKey: PrimaryKey = PrimaryKey(Invoice_id)
}

//        val Invoice_id = integer("inv_id")
//        val order_id = reference("order_id",OrderTable.order_id)
//        val Payment_id = reference("Payment_id",PaymentTable.payment_id)
//        val Date = varchar("Date",30)
//        val Discount = integer("Discount")
//        val Delivery_Charge = integer("Delivery_Charge")
//        // after Discount Count the total price of the product
//        val Total_Price = float("Total_Price")
//        val Invoice_Details = varchar("Invoice_Detail",100)