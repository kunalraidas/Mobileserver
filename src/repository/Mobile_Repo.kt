package com.example.repository

import com.example.data.model.Mobile
import com.example.data.table.MobileTable
import com.example.repository.Database_Factory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class Mobile_Repo
{
     suspend fun addMobile(mobile: Mobile){
         dbQuery {
             MobileTable.insert { mt->
                 mt[MobileTable.Mobile_id] = mobile.mobile_id
                 mt[MobileTable.Product_id] = Product_id
                 mt[MobileTable.Ram] = mobile.ram
                 mt[MobileTable.Storage] = mobile.storage
                 mt[MobileTable.Price] = mobile.price
             }
         }
     }

    suspend fun getMobileId(id : Int ) = dbQuery {
        MobileTable.select {
            MobileTable.Mobile_id.eq(id)
        }.map {
            rowToMobile(it)
        }.singleOrNull()
    }

    private fun rowToMobile(row : ResultRow) : Mobile?
    {
        return Mobile(
            mobile_id = row[MobileTable.Mobile_id],
            product_id = row[MobileTable.Product_id],
            ram = row[MobileTable.Ram],
            storage = row[MobileTable.Storage],
            price = row[MobileTable.Price],
            quentity = row[MobileTable.quentity]
        )
    }


}