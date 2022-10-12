package com.example.data.table

import org.jetbrains.exposed.sql.Table

object AdminTable : Table()
{
   val Admin_id = varchar("admin_id",10).autoIncrement()
    val Admin_name = varchar("admin_name",50)
    val Admin_email = varchar("admin_email",50)
    val Admin_password = varchar("admin_password",10)

    override val primaryKey: PrimaryKey = PrimaryKey(Admin_id)
}