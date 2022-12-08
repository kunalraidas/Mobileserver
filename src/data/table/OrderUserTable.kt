package com.example.data.table

import org.jetbrains.exposed.sql.Table

object OrderUserTable : Table() {

    val order_id = reference("order_id",OrderTable.order_id)
    val email_id = reference("email",CustomerTable.Email)
}