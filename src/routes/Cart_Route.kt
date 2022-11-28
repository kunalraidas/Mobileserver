package com.example.routes


import com.example.data.model.Product
import com.example.data.response.Simple_Response
import com.example.repository.Cart_Repo
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Route.Cart_Route(db: Cart_Repo){

    post("cart/add") {
        val email = try {
            call.request.queryParameters["email"]
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, Simple_Response(false, "Hii ${e.message}"))
            return@post
        }

        val product = try {
            call.receive<Product>()
        }catch (e:Exception){
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"Invaild Product"))
        }
        val qty = try{
            call.request.queryParameters["qty"]?.toInt()
        }catch (e : Exception){
            call.respond(HttpStatusCode.BadRequest, Simple_Response(false,"hii  qty${e.message}"))
            return@post
        }

        try{
            db.addtocart(email!!, product as Product,qty!!)
            call.respond(HttpStatusCode.OK,Simple_Response(true,"Cart Added SuccesFully"))
        }catch (e : Exception){
            call.respond(HttpStatusCode.NotAcceptable,Simple_Response(false,e.message.toString()))
        }

    }


}