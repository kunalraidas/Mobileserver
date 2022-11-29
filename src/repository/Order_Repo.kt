package com.example.repository

import com.example.data.modals.CartItems
import com.example.data.model.OrderItem

import com.example.repository.Database_Factory.dbQuery


//import com.example.data

class Order_Repo(private val productRepo: Product_Repo)
{
  suspend fun addOrder(items :CartItems,discount :Float,deliveryCharge : Float) = dbQuery {

      val orderId = "order" + System.currentTimeMillis()

      var total = 0.0
      items.Cart.forEach {
        total += it.total_price
      }



  }
   /* suspend fun addOrder(order: Order) {
        dbQuery {
            OrderTable.insert { at ->
             //   at[OrderTable.Order_id] = order.order_id
                at[OrderTable.Cart_id] = order.cart_id
                at[OrderTable.Date] = order.order_date
                at[OrderTable.Order_Status] = order.order_status
            }
        }
    }

    suspend fun findOrderByEmail(email: String) =
        dbQuery {
            OrderTable.select {
                OrderTable.Email.eq(email)
            }.map {
                rowToOrder(it)
            }.singleOrNull()
        }

    suspend fun findOrderByDate(date: String) = Database_Factory.dbQuery {
        OrderTable.select {
            OrderTable.Date.eq(date)
        }.map {

        }.singleOrNull()
    }

    private fun rowToOrder(row: ResultRow): Order? {
        if (row == null) {
            return null
        }
        return Order(
            order_id = row[OrderTable.Order_id],
            cart_id = row[OrderTable.Cart_id],
            order_date = row[OrderTable.Date],
            order_status = row[OrderTable.Order_Status]
        )
    }*/
}