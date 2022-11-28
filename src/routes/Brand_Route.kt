package com.example.routes

import com.example.data.model.Brand
import com.example.data.response.Simple_Response
import com.example.repository.Brand_Repo
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.Brand_Route(
    brandDb : Brand_Repo
)
{
    post("brand/add") {
        val brandAdd = try {
            call.receive<Brand>()
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
            return@post
        }

        try {
            val brand = Brand(brandAdd.brand_id,brandAdd.brand_name)
            brandDb.addBrand(brand)
            call.respond(HttpStatusCode.OK,Simple_Response(true,"Data Added"))
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }

    get("brand/get-brand-name") {
        val brandName = try {
            call.request.queryParameters["brandName"]
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
            return@get
        }

        try {
            val b = brandDb.findBrandByName(brandName!!)
            call.respond(HttpStatusCode.OK,b!!)
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }

    get("brand/getAll") {
        try {
            val brand = brandDb.getAllBrandName()
            call.respond(HttpStatusCode.OK,brand)
        }
        catch (e :Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }



    delete("brand/delete") {
        val brand = try {
            call.request.queryParameters["brand_id"]?.toInt()
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
            return@delete
        }

        try {
            val brand_id =brandDb.deleteBrandname(brand!!)
            call.respond(HttpStatusCode.OK,Simple_Response(true,"Product brand is deleted"))

        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }



}