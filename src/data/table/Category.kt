package com.example.data.table

import org.jetbrains.exposed.sql.Table

object Category : Table()
{
    val cate_id = varchar("cate_id",10)
    val cate_name = varchar("cate_name",50)

    override val primaryKey: PrimaryKey = PrimaryKey(cate_id)
}