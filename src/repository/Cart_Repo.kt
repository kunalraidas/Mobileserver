package com.example.repository

import com.example.data.model.Cart
import com.example.data.table.CartTable
import com.example.data.table.CustomerTable
import com.example.data.table.ProductTable
import org.jetbrains.exposed.sql.*
import com.example.repository.Database_Factory.dbQuery
open class Cart_Repo
{
    suspend fun addCartItem(cart: Cart){
        Database_Factory.dbQuery {
            CartTable.insert { ct->
                // ct[CartTable.Cart_id] = cart.cart_id
                ct[CartTable.Email] = CustomerTable.Email
                ct[CartTable.Product_id] = ProductTable.Product_id

                ct[CartTable.Quentity] = cart.quentity
                ct[CartTable.TotalPrice] = cart.total_price
            }
        }
    }
    suspend fun removeformCart(id: Int) = dbQuery{
        CartTable.deleteWhere {
            CartTable.Cart_id.eq(id)
        }
    }

    suspend fun getCart(id: Int) = dbQuery{
        CartTable.select {
            CartTable.Cart_id.eq(id)
        }
    }

    suspend fun UpdateQuentity(id: Int,qty : Int) = dbQuery {
        CartTable.update(where = { CartTable.Cart_id.eq(id) }){ t ->
            t[Quentity] = qty

        }

    }



    private fun rowToCart(row: ResultRow) : Cart?
    {
        if (row == null)
        {
            return null
        }

        return Cart(
            cart_id = row[CartTable.Cart_id],
            email = row[CartTable.Email],
            product_id = row[CartTable.Product_id],
            quentity = row[CartTable.Quentity],
            total_price = row[CartTable.TotalPrice]
        )
    }
}