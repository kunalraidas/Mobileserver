package com.example.data.table

import org.jetbrains.exposed.sql.Table

object Payment : Table()
{
    val Payment_id =  varchar("payment_id",10)

    override val primaryKey: PrimaryKey = PrimaryKey(Payment_id)
}