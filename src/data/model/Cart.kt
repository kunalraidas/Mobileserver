package com.example.data.model

data class Cart(
    val cart_id : Int,
    val email : String,
    val product_id : Int,
    val product : Product?=null,
    val quentity : Int,
    val total_price : Float,
    val mobile_id : Int? = null,
    val color_id : Int? = null,
    val accessories_id : Int? = null,
)
