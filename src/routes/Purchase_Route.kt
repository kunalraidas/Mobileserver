package com.example.routes

import com.example.data.model.Purchase
import com.example.data.response.Simple_Response
import com.example.repository.Purchase_Repo
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.Purchase_Route(
    PurchaseDb : Purchase_Repo
)
{
    post("purchase/add")
    {
        val purchase = try {
            call.receive<Purchase>()
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
            return@post
        }

        try {
            val puchse = Purchase(purchase.purchase_id,purchase.date,purchase.quantity,purchase.price)
            PurchaseDb.addPurchase(puchse)
            call.respond(HttpStatusCode.OK,Simple_Response(true,"Purchase Product"))
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }

    get("purchase/get_all_purchase") {
        try {
            val purchase = PurchaseDb.getAllPurchase()
            call.respond(HttpStatusCode.OK,purchase)
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }


    delete("purchase/delete") {
        val purchase_id = try {
            call.request.queryParameters["purchase_id"]?.toInt()
        }

        catch (e:Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
            return@delete
        }

        try{
            val purchase = PurchaseDb.deletePurchase(purchase_id!!)
            call.respond(HttpStatusCode.OK,Simple_Response(true,"Purchase Deleted"))
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }

    }





}