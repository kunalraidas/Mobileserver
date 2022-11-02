package com.example.data.model

data class Product(
    val product_id : Int,
    val product_name : String,
    val product_desc : String,
    val cate_name : String,
    val color : List<Color>,
    val brand_id : Int,

    var Mobile : Mobile? = null,
    var Accessories : Accessories? = null

)
