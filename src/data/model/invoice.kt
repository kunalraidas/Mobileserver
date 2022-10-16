package com.example.data.model

import java.sql.Date

data class invoice(
    val invoice_id:String,
    val date : String,
    val discount : Int,
    val delivery_charge : Int,
    val invoice_details: String
)
