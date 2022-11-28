package com.example.repository

import com.example.data.model.Accessories
import com.example.data.model.Product
import com.example.data.table.AccessoriesTable
import com.example.data.table.DiscountTable
import com.example.repository.Database_Factory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class Accessories_Repo
{
    suspend fun addAccessories(accessories: Accessories)
    {
        dbQuery {
            AccessoriesTable.insert { at->
                at[AccessoriesTable.Access_id] = accessories.access_id
                at[AccessoriesTable.Product_id] = Product_id
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

    suspend fun updateAccessories(accessories: Accessories){
        dbQuery {
            AccessoriesTable.update(where = {AccessoriesTable.Access_id.eq(accessories.access_id)}) { at->
                at[AccessoriesTable.Specification] = accessories.specification
                at[AccessoriesTable.Price] = accessories.price
            }
        }
    }

    suspend fun deleteAccessories(id : Int)
    {
        return dbQuery {
            AccessoriesTable.deleteWhere{
                AccessoriesTable.Access_id.eq(id)
            }
        }
    }

//    suspend fun deleteDiscount(coupon_code : String)
//    {
//        return dbQuery {
//            DiscountTable.deleteWhere {
//                DiscountTable.Coupon_code.eq(coupon_code)
//            }
//        }
//    }
}