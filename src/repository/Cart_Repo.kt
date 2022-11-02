package com.example.repository

import com.example.data.model.Cart
import com.example.data.table.CartTable
import com.example.data.table.CustomerTable
import com.example.data.table.ProductTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert

class Cart_Repo
{
    suspend fun addCartItem(cart: Cart){
        Database_Factory.dbQuery {
            CartTable.insert { ct->
                ct[CartTable.Cart_id] = cart.cart_id
                ct[CartTable.Email] = CustomerTable.Email
                ct[CartTable.Product_id] = ProductTable.Product_id
                ct[CartTable.Quentity] = cart.quentity
                ct[CartTable.TotalPrice] = cart.total_price
            }
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