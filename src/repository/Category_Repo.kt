package com.example.repository

import com.example.data.model.Category
import com.example.data.table.CategoryTable
import com.example.repository.Database_Factory.dbQuery
import org.jetbrains.exposed.sql.*

class Category_Repo
{
    suspend fun addCategory(category: Category){
        dbQuery {
            CategoryTable.insert { ct->
                ct[cate_id] = category.cate_id
                ct[cate_name] = category.cate_name
            }
        }
    }

    suspend fun findCategoryName(name: String) = dbQuery {
        CategoryTable.select{
            CategoryTable.cate_name.eq(name)
        }.map {
            rowToCategory(it)
        }.singleOrNull()
    }

    suspend fun getCategoryById(id : Int) = dbQuery {
        CategoryTable.select{
            CategoryTable.cate_id.eq(id)
        }.map {
            rowToCategory(it)
        }.singleOrNull()
    }



    suspend fun getAllCategoryName() : List<Category?> = dbQuery {
        CategoryTable.selectAll().map {
            rowToCategory(it)
        }
    }

    suspend fun deleteBrand(id : Int)
    {
        return dbQuery {
            CategoryTable.deleteWhere {
                CategoryTable.cate_id.eq(id)
            }
        }
    }


    private fun rowToCategory(row: ResultRow): Category?{
        return Category(
            cate_id = row[CategoryTable.cate_id],
            cate_name = row[CategoryTable.cate_name]
        )
    }
}




























