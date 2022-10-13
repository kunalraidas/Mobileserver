package com.example.data.table

import org.jetbrains.exposed.sql.Table

object OrderTable : Table()
{
    val Order_id = integer("order_id")
    val Cust_id =  reference("cust_id",CustomerTable.Cust_id)
  //  val IMEI_No = varchar("pv_id",10).references(MobileTable.imei_number)
// val Access_id =
    val order_status = varchar("order_status",30)
    val Payment_id = varchar("payment_id",10).references(PaymentTable.Payment_id)
    val Invoice_id = reference("invoice_id",InvoiceTable.inv_id)

    override val primaryKey: PrimaryKey = PrimaryKey(Order_id)
}