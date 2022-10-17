package com.example.data.modals

data class Registration_Request(
    val email : String,
    val password : String,
    val first_name : String,
    val last_name : String,
    val phone_no : Long,
    val cust_address : String,
    val delivery_address : String,
    val pincode : Int
)
