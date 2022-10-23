package com.example.repository

import com.example.data.model.Product
import com.example.data.table.ProductTable
import com.example.repository.Database_Factory.dbQuery
import org.jetbrains.exposed.sql.insert

class Product_Repo
{
    suspend fun addproduct(product: Product)
    {
        dbQuery {
            ProductTable.insert { pt->
                pt[ProductTable.Product_name] = product.product_name

            }
        }
    }
}