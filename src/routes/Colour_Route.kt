package com.example.routes

import com.example.data.model.Color
import com.example.data.response.Simple_Response
import com.example.repository.Color_Repo
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import sun.security.ec.CurveDB

@Suppress("UNREACHABLE_CODE")
fun Route.Colour_Route(db: Color_Repo){

    post("colour/add") {
        val addColor = try{
            call.receive<Color>()
        }catch (e:Exception){
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
            return@post
        }

        try {
            val color = Color(addColor.color_id,addColor.color_name,addColor.product_image)
            db.addColor(color)
            call.respond(HttpStatusCode.OK,Simple_Response(true,"Colour Added Successfully"))
        }catch (e:Exception){
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }
}

