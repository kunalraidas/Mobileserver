package com.example.data.table

import org.jetbrains.exposed.sql.Table

object AdminTable : Table()
{
    val Admin_name = varchar("admin_name",50)
    val Admin_email = varchar("admin_email",50)
    val Admin_password = varchar("admin_password",100)

    override val primaryKey: PrimaryKey = PrimaryKey(Admin_email)
}