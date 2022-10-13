package com.example.data.table

import org.jetbrains.exposed.sql.Table

object CategoryTable : Table()
{
    val Cate_id = integer("cate_id")
    val Cate_name = varchar("cate_name",50)

    override val primaryKey: PrimaryKey = PrimaryKey(Cate_id)
}