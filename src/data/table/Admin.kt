package com.example.data.table

import org.jetbrains.exposed.sql.Table

object Admin : Table()
{
   val admin_id = varchar("admin_id",10)
    val admin_name = varchar("admin_name",50)
    val admin_email = varchar("admin_email",50)
    val admin_password = varchar("admin_password",10)

    override val primaryKey: PrimaryKey = PrimaryKey(admin_id)
}