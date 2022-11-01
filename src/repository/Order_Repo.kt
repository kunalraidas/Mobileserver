package com.example.repository

import com.example.data.model.Order
import com.example.data.table.OrderTable
import org.jetbrains.exposed.sql.insert

class Order_Repo {
    suspend fun addOrder(order: Order)
    {
        Database_Factory.dbQuery{
            OrderTable.insert { at ->
            at[OrderTable.Order_id] = order.order_id
            at[OrderTable.Cart_id] = order.cart_id
            at[OrderTable.Date] = order.order_date
            at[OrderTable.Order_Status] = order.order_status

            }
        }
    }

    class OrderRepo(private val productRepo: Product_Repo) : Mobile_Repo() , Accessories_Repo() {

        //add user's order
        suspend fun addOrder(items: List<CartItem>) = dbQuery {

            val orderId = "order" + System.currentTimeMillis()

            var total = 0.0
            items.forEach {
                total += it.totalPrice
            }
        }