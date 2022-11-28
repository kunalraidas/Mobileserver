package com.example.data.model

import java.time.LocalDate

data class Order(
    val order_id : Int,
    val cart_id :  Int,
    val order_date : LocalDate,
    val order_status : String
)
