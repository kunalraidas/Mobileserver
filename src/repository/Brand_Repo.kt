package com.example.repository

import com.example.data.model.Brand
import com.example.data.table.BrandTable
import com.example.data.table.CustomerTable
import com.example.repository.Database_Factory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

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

    private fun rowToBrand(row: ResultRow): Brand?
    {
        return Brand(
            brand_id = row[BrandTable.brand_id],
            brand_name =  row[BrandTable.brand_name]
        )
    }


//    suspend fun findCustomerByEmail(email : String) = dbQuery {
//        CustomerTable.select { CustomerTable.Email.eq(email) }
//            .map {
//                rowToCustomer(it) }
//            .singleOrNull()
//    }

}