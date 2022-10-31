package com.example.data.table

import org.jetbrains.exposed.sql.Table

object ColorTable :Table()
{
    val Color_id = integer("color_id")
    val Color_name = varchar("color_name",50)
    val Product_Image = varchar("image_url",128)
    val Product_id = reference("product_id",ProductTable.Product_id)

    override val primaryKey: PrimaryKey = PrimaryKey(Color_id)

}