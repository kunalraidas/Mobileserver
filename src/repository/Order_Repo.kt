package com.example.repository

import com.example.data.model.Cart
import com.example.data.model.Order
import com.example.data.table.OrderTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import com.example.repository.Database_Factory.dbQuery

import org.jetbrains.exposed.sql.ResultRow

//import com.example.data

class Order_Repo
{
    suspend fun addOrder(cart : List<Cart>) = dbQuery {
        val order_id = "order" + System.currentTimeMillis()

        var total = 0.0
        cart.forEach()
        {
            total += it.total_price
        }


    }

//    suspend fun findOrderByEmail(email: String) =
//        dbQuery {
//            OrderTable.select {
//                OrderTable.Email.eq(email)
//            }.map {
//                rowToOrder(it)
//            }.singleOrNull()
//        }

//    suspend fun findOrderByDate(date: String) = Database_Factory.dbQuery {
//        OrderTable.select {
//            OrderTable.Date.eq(date)
//        }.map {
//
//        }.singleOrNull()
//    }

//    private fun rowToOrder(row: ResultRow): Order? {
//        if (row == null) {
//            return null
//        }
//        return Order(
//            order_id = row[OrderTable.Order_id],
//            cart_id = row[OrderTable.Cart_id],
//            order_date = row[OrderTable.Date],
//            order_status = row[OrderTable.Order_Status]
//        )

}