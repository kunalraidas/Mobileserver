package com.example.data.table

import org.jetbrains.exposed.sql.Table

object ColorTable :Table()
{
    val Color_id = integer("color_id")
    val Color_name = varchar("color_name",50)
    val Image_url1 = varchar("image_url1",50)
    val Image_url2 = varchar("image_url2",50)
    val Image_url3 = varchar("image_url3",50)

    override val primaryKey: PrimaryKey = PrimaryKey(Color_id)

}