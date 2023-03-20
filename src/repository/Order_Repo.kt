package com.example.repository

import com.example.data.model.Cart
import com.example.data.model.Order
import com.example.data.model.OrderItem
import com.example.data.table.CartTable
import com.example.data.table.OrderTable
import com.example.data.table.OrderUserTable
import com.example.data.table.Order_Product_table
import com.example.repository.Database_Factory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDate


//import com.example.data

class Order_Repo {

    suspend fun addOrder(items: List<Cart>) = dbQuery {

            val Order_Id = "order_id" + System.currentTimeMillis()

            var total = 0.0
            items.forEach{
                total += it.total_price
            }

            OrderTable.insert { order->
                order[OrderTable.order_id] = Order_Id
                order[email] = items[0].email
                order[order_date] = LocalDate.now()
                order[orderStatus] = 0
                order[paymentStatus] = 0
                order[OrderTable.total] = total.toFloat()
                order[discount] = 0.0.toFloat()
                order[deliveryCharge] = 0.0.toFloat()
                order[total_received] = 0.0.toFloat()
            }

            items.forEach{
                Order_Product_table.insert { op->
                    op[Order_Product_table.order_id] = Order_Id
                    op[Order_Product_table.product_id] = it.product_id
                    op[Order_Product_table.quentity] =  it.quentity
                    op[Order_Product_table.total_price] = it.total_price
                }

                items.forEach{
                    OrderUserTable.insert { u->
                        u[OrderUserTable.order_id] = Order_Id
                        u[OrderUserTable.email_id] = items[0].email
                    }
                }

                // Cart Deleted
                  CartTable.deleteWhere {
                      CartTable.Cart_id.eq(it.cart_id)
                   }

            }
        }

    // Delete Order
    suspend fun deleteOrder(orderId : String) = dbQuery {
        OrderTable.deleteWhere {
            OrderTable.order_id.eq(orderId)
        }
    }

    //Get Order by id
    suspend fun getOrderById(orderId: String): Order? = dbQuery {
        OrderTable.select{
            OrderTable.order_id.eq(orderId)
        }.map {
            rowToOrder(it)
        }.singleOrNull()
    }

    suspend fun getOrderByStatus(status : Int):List<Order> = dbQuery {
        OrderTable.select {
            OrderTable.orderStatus.eq(status)
        }.map {
            rowToOrder(it)
        }
    }

    suspend fun getOrderByEmail(email : String): List<Order> = dbQuery {
        OrderTable.select {
            OrderTable.email.eq(email)
        }.map {
            rowToOrder(it)
        }
    }

    fun getProductIdByOrderId(odi : String): List<Int> = transaction {
        var ids = Order_Product_table.slice(Order_Product_table.product_id).select {
            Order_Product_table.order_id.eq(odi)
        }.map {
            it[Order_Product_table.product_id]
        }
        ids
    }

    suspend fun updateStatus(order: Order) = dbQuery {
        OrderTable.update(where = { OrderTable.order_id.eq(order.order_id)  }){ up->
            up[OrderTable.orderStatus] = order.orderStatus
            up[OrderTable.paymentStatus] = order.paymentStatus
            up[OrderTable.trackingUrl] = order.trackingUrl
        }
    }

    suspend fun getAllOrder() : List<Order> = dbQuery {
        OrderTable.selectAll().map {
            rowToOrder(it)
        }
    }

    suspend fun filterOrder(startDate : LocalDate,endDate : LocalDate) : List<Order> = dbQuery {
        OrderTable.select {
            OrderTable.order_date.between(startDate,endDate)
        }.map {
            rowToOrder(it)
        }
    }


    fun rowToOrder(row: ResultRow):Order{

        var orderList = mutableListOf<OrderItem>()
        var ids = getProductIdByOrderId(row[OrderTable.order_id])

        ids.forEach{
            orderList.add(
                OrderItem(
                    product = Product_Repo().getOneProduct(it),
                    totalPrice = 0.0f,
                    quantity = 0
                )
            )
        }

        return  Order(
            order_id = row[OrderTable.order_id],
            Email = row[OrderTable.email],
            order_date = row[OrderTable.order_date],
            trackingUrl = row[OrderTable.trackingUrl],
            orderStatus = row[OrderTable.orderStatus],
            paymentStatus = row[OrderTable.paymentStatus],
            total = row[OrderTable.total],
            orderItems = orderList,
            quantity = ids.size,
            deliveryCharge = row[OrderTable.deliveryCharge],
            totalrecived = row[OrderTable.total_received]
        )
    }

}



//    suspend fun addOrder(cart: List<Cart>) = dbQuery {
//        val order_id = "order" + System.currentTimeMillis()
//        suspend fun addOrder(items: CartItems, discount: Float, deliveryCharge: Float) = dbQuery {
//
//            val orderId = "order" + System.currentTimeMillis()
//            var total = 0.0
//            items.Cart.forEach {
//                total += it.total_price
//            }
//
//            OrderTable.insert { t->
//                t[OrderTable.order_id] = orderId
//                t[OrderTable.]
//            }
//
//        }
//    }

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

//}
}
  */