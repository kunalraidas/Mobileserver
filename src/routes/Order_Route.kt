package com.example.routes


import com.example.data.modals.CartList
import com.example.data.modals.DateFilter
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
            val cart = try {
                call.receive<CartList>()
            } catch (e:Exception) {
                call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
                return@post
            }
            try {
                orderdb.addOrder(cart.Cart)
                call.respond(HttpStatusCode.OK,Simple_Response(true,"Order Placed Successfully"))
            } catch (e: Exception) {
                call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
            }
        }

   post("order/get_email_order") {
       val email = try {
           call.request.queryParameters["email"]!!
       }
       catch (e : Exception)
       {
           call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
           return@post
       }
       try {
           val order_list = orderdb.getOrderByEmail(email)
           call.respond(HttpStatusCode.OK,order_list)
       }
       catch (e : Exception)
       {
           call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
       }
   }

    post("order/update") {
        val order = try {
            call.receive<Order>()
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
            return@post
        }

        try {
            orderdb.updateStatus(order)
            call.respond(HttpStatusCode.OK,Simple_Response(true,"Status Update"))
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }

    post("order/status") {

        val status = try {
            call.request.queryParameters["status"]!!.toInt()
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"Please Provide Status"))
            return@post
        }

        try {
            val order = orderdb.getOrderByStatus(status)
            if (order.isEmpty())
            {
                call.respond(HttpStatusCode.NotFound,Simple_Response(false,"No order in this status $order"))
            }
            else
            {
                call.respond(HttpStatusCode.OK,Simple_Response(true,"Done$order"))
            }
        }catch (e : Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }

    get("order/id") {
        val id = try {
            call.request.queryParameters["id"]!!
        }catch (e : Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
            return@get
        }
        try {
            val order = orderdb.getOrderById(id)
            if (order == null)
            {
                call.respond(HttpStatusCode.NotFound,Simple_Response(false, "No Order Found$order"))
            }
            else{
                call.respond(HttpStatusCode.OK,Simple_Response(true,"Done$order"))
            }
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
        }
    }

    post("order/remove") {
        val orderId = try {
            call.request.queryParameters["order_id"]!!
        } catch (e : Exception) {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
            return@post
        }
        try {
            orderdb.deleteOrder(orderId)
            call.respond(HttpStatusCode.OK,Simple_Response(true,"Order Deleted"))
        } catch (e : Exception) {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }

    get("order/getAll") {
        try {
            val list = orderdb.getAllOrder()
            call.respond(HttpStatusCode.OK,list)
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }

   post("order/filter") {
       val date = try {
           call.receive<DateFilter>()
       }
       catch (e : Exception){
           call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
           return@post
       }

       try {
           val filter =orderdb.filterOrder(date.startDate,date.endDate)
           call.respond(HttpStatusCode.OK,filter)
       }
       catch (e : Exception)
       {
           call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
       }
   }

}