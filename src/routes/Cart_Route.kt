package com.example.routes


import com.example.data.model.Product
import com.example.data.response.Simple_Response
import com.example.repository.Cart_Repo
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Route.Cart_Route(CartDb: Cart_Repo){

    post("cart/add") {
        val email = try {
            call.request.queryParameters["email"]
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, Simple_Response(false, "Hii ${e.message}"))
            return@post
        }

        val qty = try{
            call.request.queryParameters["qty"]?.toInt()
        }catch (e : Exception){
            call.respond(HttpStatusCode.BadRequest, Simple_Response(false,"hii  qty${e.message}"))
            return@post
        }

        val product = try {
            call.receive<Product>()
        }catch (e:Exception){
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"Invaild Product"))
            return@post
        }

        try{
            CartDb.addtocart(email!!,product ,qty!!)
            call.respond(HttpStatusCode.OK,Simple_Response(true,"Cart Added SuccessFully"))
        }catch (e : Exception){
            call.respond(HttpStatusCode.NotAcceptable,Simple_Response(false,e.message.toString()))
        }
    }

    post("cart/update") {
       val email = try {
           call.request.queryParameters["email"]
       } catch (e : Exception)
       {
           call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
           return@post
       }

        val  product = try {
            call.receive<Product>()
        } catch (e : Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
            return@post
        }
        val qty = try {
            call.request.queryParameters["qty"]?.toInt()
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
            return@post
        }
        try{
            CartDb.updateQuentity(email!!,product,qty!!)
            call.respond(HttpStatusCode.OK,Simple_Response(true,"Quantity updated "))
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
        }
    }

    delete("cart/delete") {
        val cartId = try {
            call.request.queryParameters["cart_id"]?.toInt()
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
            return@delete
        }

        try {
            val cart_Id = CartDb.removeCart(cartId!!)
            call.respond(HttpStatusCode.OK,Simple_Response(true,"Cart Deleted successfully"))
            call.respond(HttpStatusCode.OK,cart_Id)
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }

    get("cart/get_cart_by_email") {
            val email  = try {
                call.request.queryParameters["email"]!!
            }
            catch (e : Exception)
            {
                call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
                return@get
            }
        try {
            val cart = CartDb.getCart(email)
          //  call.respond(HttpStatusCode.OK,Simple_Response(true,"Cart Deleted Successfully"))
            call.respond(HttpStatusCode.OK,cart)
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
        }
    }







}