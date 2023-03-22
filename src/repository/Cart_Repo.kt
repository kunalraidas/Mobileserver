package com.example.repository

import com.example.data.model.Cart
import com.example.data.model.Product
import com.example.data.table.CartTable
import com.example.repository.Database_Factory.dbQuery
import org.jetbrains.exposed.sql.*

class Cart_Repo
{
    suspend fun addtocart(email : String, product: Product, qty : Int) = dbQuery {
           var p = 0f
            if (product.Mobile != null) {
                p = product.Mobile!![0].price
            }
           else {
                p = product.Accessories!![0].price
            }
            CartTable.insert { c->
                c[CartTable.Email] = email
                c[CartTable.Product_id] = product.product_id
                c[CartTable.Quentity] = qty
                c[CartTable.TotalPrice] = qty * p
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



    suspend fun updateQuentity(email: String, product: Product,qty: Int) = dbQuery {
        var price = 0f
        if (product.Mobile != null)
        {
            price = product.Mobile!![0].price
        }
        else
        {
            price = product.Accessories!![0].price
        }

        CartTable.update(where = {CartTable.Email.eq(email) and CartTable.Product_id.eq(product.product_id)}) { ct->
            ct[CartTable.Quentity] = qty
            ct[CartTable.TotalPrice] = qty * price
        }

    }

    suspend fun  removeCart(cartId : Int) = dbQuery {
        CartTable.deleteWhere {
            CartTable.Cart_id.eq(cartId)
        }
    }

    suspend fun getCart(email: String) : List<Cart> = dbQuery {
        CartTable.select {
            CartTable.Email.eq(email)
        }.map {
            rowToCart(it)
        }
    }

    private fun rowToCart(row: ResultRow) : Cart
    {
        return Cart(
            cart_id = row[CartTable.Cart_id],
            email = row[CartTable.Email],
            product_id = row[CartTable.Product_id],
            quentity = row[CartTable.Quentity],
            total_price = row[CartTable.TotalPrice]
        )
    }
}