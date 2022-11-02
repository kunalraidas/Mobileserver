package com.example.data.model

import java.util.Date

data class Order(
    val order_id : Int,
    val cart_id :  Int,
    val order_date : String,
    val order_status : String
)
