package com.example.data.table

import org.jetbrains.exposed.sql.Table

object CategoryTable : Table()
{
    val cate_id = integer("cate_id").autoIncrement()
    val cate_name = varchar("cate_name",30)

    override val primaryKey: PrimaryKey = PrimaryKey(cate_id)
}