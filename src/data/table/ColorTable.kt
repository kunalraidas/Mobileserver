package com.example.data.table

import org.jetbrains.exposed.sql.Table

object ColorTable :Table()
{
    val Color_id = integer("color_id")
    val Color_name = varchar("color_name",50)
    val Product_Image = varchar("image_url1",50)


    override val primaryKey: PrimaryKey = PrimaryKey(Color_id)

}