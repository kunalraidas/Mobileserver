package com.example.repository

import com.example.data.model.Brand
import com.example.data.table.BrandTable
import com.example.repository.Database_Factory.dbQuery
import org.jetbrains.exposed.sql.*


class Brand_Repo
{
    suspend fun addBrand(brand: Brand){
       dbQuery {
           BrandTable.insert { bt->
               bt[BrandTable.brand_id] = brand.brand_id
               bt[BrandTable.brand_name] = brand.brand_name
           }
       }
    }

    suspend fun findBrandByName(name : String) = dbQuery {
        BrandTable.select {
            BrandTable.brand_name.eq(name)
        }.map {
            rowToBrand(it)
        }.singleOrNull()
    }

    suspend fun getAllBrandName() : List<Brand?> = dbQuery {
        BrandTable.selectAll().map {
            rowToBrand(it)
        }
    }

    suspend fun deleteBrandname(id : Int)
    {
        return dbQuery {
            BrandTable.deleteWhere {
                BrandTable.brand_id.eq(id)
            }
        }
    }



    private fun rowToBrand(row: ResultRow): Brand?
    {
        return Brand(
            brand_id = row[BrandTable.brand_id],
            brand_name =  row[BrandTable.brand_name]
        )
    }

}