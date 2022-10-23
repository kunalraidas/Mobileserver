package com.example.data.table

import org.jetbrains.exposed.sql.Table

object IMEI_NO_Table : Table()
{
    val IMEI_NO = long("IMEI_NO")
    val Mobile_id = reference("Mobile_id",MobileTable.Mobile_id)

    override val primaryKey: PrimaryKey = PrimaryKey(IMEI_NO)
}