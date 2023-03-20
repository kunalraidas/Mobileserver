package com.example.routes

import com.example.data.model.ProductColor
import com.example.data.response.Simple_Response
import com.example.repository.Color_Repo
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

@Suppress("UNREACHABLE_CODE")
fun Route.Colour_Route(db: Color_Repo){

    post("colour/add") {
        val addProductColor = try
        {
            call.receive<ProductColor>()
        }
        catch (e:Exception)

        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
            return@post
        }
        try
        {
            val productColor = ProductColor(addProductColor.color_id,addProductColor.color_name,addProductColor.product_image)
            db.addColor(productColor)
            call.respond(HttpStatusCode.OK,Simple_Response(true,"Colour Added Successfully"))
        }
        catch (e:Exception){
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }
}

