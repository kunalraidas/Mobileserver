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
            at[OrderTable.Cart_id] = Cart_id
            at[OrderTable.Date] = order.order_date
            at[OrderTable.Order_Status] = order.order_status
            }
        }
    }

}