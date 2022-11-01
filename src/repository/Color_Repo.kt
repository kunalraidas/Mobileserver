package com.example.repository

import com.example.data.model.Color
import com.example.data.table.ColorTable
import com.example.repository.Database_Factory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class Color_Repo
{
    suspend fun addColor(color: Color)
    {
        dbQuery {
            ColorTable.insert { ct->
                ct[ColorTable.Color_id] = color.color_id
                ct[ColorTable.Color_name] = color.color_name
                ct[ColorTable.Product_Image] = color.product_image
                ct[ColorTable.Product_id] = Product_id
            }
        }
    }

    suspend fun findColorById(id : Int) = dbQuery {
        ColorTable.select {
            ColorTable.Color_id.eq(id)
        }.map {
            rowToColor(it)
        }.singleOrNull()
    }

    private fun rowToColor(row: ResultRow):Color?
    {
        return Color(
            color_id =  row[ColorTable.Color_id],
            color_name = row[ColorTable.Color_name],
            product_image = row[ColorTable.Product_Image]
        )
    }


}