package com.example.routes

import com.example.data.model.IMEI_no
import com.example.data.response.Simple_Response
import com.example.repository.IMEI_NO_Repo
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.IMEINO_Route(
    IMEIDB : IMEI_NO_Repo
)
{
   // Add IMEI NUMBER
    post("imei_no/add") {
        val addIMEINO = try {
            call.receive<IMEI_no>()
        }

        catch (e : Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
            return@post
        }

        try {
            val imei = IMEI_no(addIMEINO.imei_no,addIMEINO.mobile_id)
            IMEIDB.addIMEI(imei)
            call.respond(HttpStatusCode.OK,Simple_Response(true,"IMEI Number Added"))
        }

        catch (e : Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }
    // get imei by mobile id
    get("imei_no/mobile_id") {
        try {

        }
        catch (e : Exception)
        {

        }
        try {

        }
        catch (e : Exception)
        {

        }
    }
    // get all imei number
    get("imei_no/get_all_imei_no") {
        try
        {
            val imei = IMEIDB.getAllIMEINo()
            call.respond(HttpStatusCode.OK,imei)
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }

    // delete imei number
    delete("imei-no/delete") {
        val imei = try {
            call.request.queryParameters["imei_no"]?.toLong()
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
            return@delete
        }

        try {
                val imei = IMEIDB.deleteIMEINo(imei!!)
            call.respond(HttpStatusCode.OK,Simple_Response(true,"IMEI No Deleted"))
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }


}

