package com.example.data.table

import org.jetbrains.exposed.sql.Table

object UserInvoiceTable : Table() {

    val invoice_id = reference("invoice_id",InvoiceTable.Invoice_id)
    val email = reference("email",CustomerTable.Email)

}