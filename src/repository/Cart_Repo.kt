package com.example.repository

import com.example.data.model.Cart
import com.example.data.table.CartTable
import com.example.repository.Database_Factory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

//TODO : complete cart
class Cart_Repo {
    suspend fun addItemInCart(cart: Cart)
    {
        dbQuery{
            CartTable.insert { ct->
                ct[CartTable.Cart_id] = cart.cart_id
                ct[CartTable.Email] = Email
                ct[CartTable.Product_id] = Product_id
                ct[CartTable.Quentity] = cart.quentity
                ct[CartTable.TotalPrice] = cart.total_price
            }
        }
    }
    suspend fun findCartById(id : Int) = dbQuery{
        CartTable.select {
            CartTable.Cart_id.eq(id)
        }.map {
            rowToCart(it)
        }.singleOrNull()
        }
    }

    private fun rowToCart(row: ResultRow) : Cart?
    {
        return Cart(
            cart_id = row[CartTable.Cart_id],
            quentity = row[CartTable.Quentity],
            total_price = row[CartTable.TotalPrice],
            product_id = row[CartTable.Product_id],
            Email = row[CartTable.Email]
        )
    }

