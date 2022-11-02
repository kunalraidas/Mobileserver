package com.example.repository

import com.example.data.model.Order
import com.example.data.table.OrderTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import com.example.repository.Database_Factory.dbQuery

import org.jetbrains.exposed.sql.ResultRow

//import com.example.data

class Order_Repo
{
    suspend fun addOrder(order: Order)
    {
        dbQuery{
            OrderTable.insert { at ->
            at[OrderTable.Order_id] = order.order_id
            at[OrderTable.Cart_id] = order.cart_id
            at[OrderTable.Date] = order.order_date
            at[OrderTable.Order_Status] = order.order_status
            }
        }
    }
    suspend fun findOrderByEmail(email : String) =
        dbQuery{
            OrderTable.select {
                OrderTable.Email.eq(email)
            }.map {
                rowToOrder(it)
            }.singleOrNull()
        }

    suspend fun  findOrderByDate(date: String) = Database_Factory.dbQuery {
        OrderTable.select {
            OrderTable.Date.eq(date)
        }.map {

        }.singleOrNull()
    }

    private fun rowToOrder(row: ResultRow) : Order?
    {
        if (row == null)
        {
            return null
        }
        return Order(
            order_id = row[OrderTable.Order_id],
            cart_id = row[OrderTable.Cart_id],
            order_date = row[OrderTable.Date],
            order_status = row[OrderTable.Order_Status]
        )
    }
// private fun rowToCart(row: ResultRow) : Cart?
//    {
//        if (row == null)
//        {
//            return null
//        }
//
//        return Cart(
//            cart_id = row[CartTable.Cart_id],
//            email = row[CartTable.Email],
//            product_id = row[CartTable.Product_id],
//            quentity = row[CartTable.Quentity],
//            total_price = row[CartTable.TotalPrice]
//        )
//    }//



    }
// TODO: ORDER INCOMPLETE
//TODO : SUPPLIER ADD KA RAPO AND ROUTE