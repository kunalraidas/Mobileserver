package com.example.data.table

import io.ktor.util.*
import org.jetbrains.exposed.sql.Table

object Color :Table()
{
    val color_id = varchar("color_id",10)
    val color_name = varchar("color_name",50)
//    val color_image = varchar("color_image",100)
}