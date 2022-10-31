package com.example.repository

import com.example.data.model.Accessories
import com.example.data.table.AccessoriesTable
import com.example.repository.Database_Factory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class Accessories_Repo
{
    suspend fun addAccessories(accessories: Accessories)
    {
        dbQuery {
            AccessoriesTable.insert { at->
                at[AccessoriesTable.Access_id] = accessories.access_id
                at[AccessoriesTable.Product_id] = Product_id
                at[AccessoriesTable.Color_id] = Color_id
                at[AccessoriesTable.Specification] = accessories.specification
            }
        }
    }

    suspend fun getAccessoriesById(id : Int) = dbQuery {
        AccessoriesTable.select {
            AccessoriesTable.Access_id.eq(id)
        }.map {
            rowToAccessories(it)
        }.singleOrNull()
    }

    private fun rowToAccessories(row: ResultRow):Accessories?
    {
        return Accessories(
            access_id = row[AccessoriesTable.Access_id],
            specification = row[AccessoriesTable.Specification],
            price = row[AccessoriesTable.Price]
        )
    }
}