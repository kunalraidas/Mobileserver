package com.example.repository

import com.example.data.model.Purchase
import com.example.data.table.ProductTable
import com.example.data.table.PurchaseTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class Purchase_Repo
{
    suspend fun addPurchase(purchase: Purchase)
    {
        Database_Factory.dbQuery {
            PurchaseTable.insert { pt->
                pt[PurchaseTable.Purchase_id] = purchase.purchase_id
                pt[PurchaseTable.Product_id] = ProductTable.Product_id
                pt[PurchaseTable.Date] = purchase.date
                pt[PurchaseTable.Quantity] = purchase.quantity
                pt[PurchaseTable.Price] = purchase.price
            }
        }
    }

    suspend fun findPurchaseId(id : Int) = Database_Factory.dbQuery {
        PurchaseTable.select {
            PurchaseTable.Purchase_id.eq(id)
        }.map {
            rowToPurchase(it)
        }.singleOrNull()
    }

    private fun rowToPurchase(row: ResultRow):Purchase?{
        if (row == null)
        {
            return null
        }
        return Purchase(
            purchase_id = row[PurchaseTable.Purchase_id],
            date = row[PurchaseTable.Date],
            quantity = row[PurchaseTable.Quantity],
            price = row[PurchaseTable.Price]
        )
    }


}