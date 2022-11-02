package com.example.routes

import com.example.data.model.Cart
import com.example.data.response.Simple_Response
import com.example.repository.Cart_Repo
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Route.Cart_Route(db: Cart_Repo){

    post("cart/add") {
        val addCart = try {
            call.receive<Cart>()
        }
        catch (e:Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
            return@post
        }

        try
        {
            val cart = Cart(addCart.cart_id,addCart.email,
                addCart.product_id,
                addCart.quentity,addCart.total_price)
              db.addCartItem(cart)
            call.respond(HttpStatusCode.Conflict,Simple_Response(true,"Cart added successfully"))
        }
        catch (e : Exception){
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }


}