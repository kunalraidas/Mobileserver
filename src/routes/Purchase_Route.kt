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
    post("purchase/add") {
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


}