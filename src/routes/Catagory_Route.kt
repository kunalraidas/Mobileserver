package com.example.routes

import com.example.data.model.Category
import com.example.data.response.Simple_Response
import com.example.repository.Category_Repo
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.Category_Route(
    cateDb : Category_Repo
){
    post("category/add") {
        val categoryAdd = try {
            call.receive<Category>()
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
            return@post
        }

        try {
            val category = Category(
                categoryAdd.cate_id,categoryAdd.cate_name
            )
            cateDb.addCategory(category)
            call.respond(HttpStatusCode.OK,Simple_Response(true,"Category Added Successfully"))
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }

    get("category/get-category-name") {
        val categoryName = try {
            call.request.queryParameters["cate_id"]
        }
        catch(e : Exception) {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
            return@get
        }
        try {
            val c = cateDb.findCategoryName(categoryName!!)
            call.respond(HttpStatusCode.OK,c!!)
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }

    get("category/getAll") {
        try {
            val category = cateDb.getAllCategoryName()
            call.respond(HttpStatusCode.OK,category)
        }
        catch (e :Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }
}


























