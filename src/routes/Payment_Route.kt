package com.example.routes

import com.example.data.modals.DateFilter
import com.example.data.model.Order
import com.example.data.response.Simple_Response
import com.example.repository.Payment_Repo
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Route.Payment_Route(
    payDb : Payment_Repo
){
    post("payment/add") {

        val order = try {
            call.receive<Order>()
        } catch (e : Exception){
          call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
          return@post
        }

        val delivery_charge = try {
            call.request.queryParameters["deliveryCharge"]!!.toFloat()
        }catch (e : Exception) {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"Enter Delivery Charge"))
            return@post
        }

        try {
            payDb.addTransaction(order,delivery_charge)
            call.respond(HttpStatusCode.OK,Simple_Response(true,"Payment Successfully complete"))
        }catch (e : Exception){
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }

    get("payment/getAll") {
        try {
            val all = payDb.getAllTransaction()
            call.respond(HttpStatusCode.OK,all)
        }catch (e : Exception){
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
        }
    }

    post("payment/filter") {
        val date = try {
            call.receive<DateFilter>()
        }catch (e : Exception){
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
            return@post
        }
        try {
            val pay = payDb.filterPayment(date.startDate,date.endDate)
            call.respond(HttpStatusCode.OK,pay)
        }catch (e : Exception){
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }

}

