package com.example.data.table

import org.jetbrains.exposed.sql.Table

object Color :Table()
{
    val Color_id = varchar("color_id",10)
    val Color_name = varchar("color_name",50)
//    val color_image = varchar("color_image",100)

    override val primaryKey: PrimaryKey = PrimaryKey(Color_id)

}