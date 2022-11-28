package com.example.routes

import com.example.data.model.Pincode
import com.example.data.response.Simple_Response
import com.example.repository.Pincode_Repo
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Route.Pincode_Route(
    pincodeDB : Pincode_Repo
)
{
    // Add pincode in database
    post("pincode/add") {
        val pin = try {
            call.receive<Pincode>()
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
            return@post
        }

        try {
            val pincode = Pincode(pin.pincode,pin.area_name,pin.delivery_charge)
            pincodeDB.addPincode(pincode)
            call.respond(HttpStatusCode.OK,Simple_Response(true,"Pincode Added"))
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }

    // Get All Pincode
    get("pincode/get_all_pin_code") {
        try {
            val pincode = pincodeDB.getAllPincode()
            call.respond(HttpStatusCode.OK,pincode)
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }



    delete("pincode/delete") {
        val pincode =  try {
            call.request.queryParameters["pincode"]?.toInt()
        }
        catch (e :Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
            return@delete
        }

        try {
            val pin_code = pincodeDB.deletePincode(pincode!!)
            call.respond(HttpStatusCode.OK,Simple_Response(true,"Pin code deleted"))

        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }

    //    delete("discount/delete") {
//        val discount = try {
//            call.request.queryParameters["coupon_code"]
//        }
//        catch (e : Exception)
//        {
//            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
//            return@delete
//        }
//
//        try {
//            val coupon_code = discountDB.deleteDiscount(discount!!)
//            call.respond(HttpStatusCode.OK,Simple_Response(true,"Coupon Code Deleted"))
//        }
//        catch (e : Exception)
//        {
//            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
//        }
//    }
}
