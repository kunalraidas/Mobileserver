package com.example.routes

import com.example.data.model.Order
import com.example.data.response.Simple_Response
import com.example.repository.Order_Repo
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.Order_Route(
    orderdb: Order_Repo
){
        post("order/add") {
            val order = try {
                call.receive<Order>()
            }
            catch (e:Exception)
            {
                call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
                return@post
            }

            try
            {
                val OrderDb = Order(order.order_id,order.cart_id,order.order_date,order.order_status)
                //  orderdb.addOrder(OrderDb)
                call.respond(HttpStatusCode.OK,Simple_Response(true,"Order Successfully"))
            }
            catch (e: Exception)
            {
                call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
            }
        }
    post("order/findEmail") {
        val email = try {
            call.receive<Order>()
        }catch (e:Exception){

        }

    }


}