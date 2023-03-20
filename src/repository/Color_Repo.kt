package com.example.repository

import com.example.data.model.ProductColor
import com.example.data.table.ColorTable
import com.example.repository.Database_Factory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class Color_Repo
{
    suspend fun addColor(productColor: ProductColor)
    {
        dbQuery {
            ColorTable.insert { ct->
                ct[ColorTable.Color_id] = productColor.color_id
                ct[ColorTable.Color_name] = productColor.color_name
                ct[ColorTable.Product_Image] = productColor.product_image
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

    private fun rowToColor(row: ResultRow):ProductColor?
    {
        return ProductColor(
            color_id =  row[ColorTable.Color_id],
            color_name = row[ColorTable.Color_name],
            product_image = row[ColorTable.Product_Image]
        )
    }


}