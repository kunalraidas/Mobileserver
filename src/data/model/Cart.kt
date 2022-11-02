package com.example.data.model

data class Cart(
    val cart_id : Int,
    var Email : String,
    var product_id : Int,
    val quentity : Int,
//    val unit_price : Float,
    val total_price : Float
)
