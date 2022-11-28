package com.example.routes

import com.example.data.model.Discount
import com.example.data.response.Simple_Response
import com.example.repository.Discount_Repo
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Route.Discount_Route(
    discountDB : Discount_Repo
)
{
    post("discount/add") {
        val addDiscount = try {
            call.receive<Discount>()
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
            return@post
        }

        try {
            val discount = Discount(addDiscount.coupon_code,addDiscount.discount_perc,addDiscount.total_coupon)
            discountDB.addDiscount(discount)
            call.respond(HttpStatusCode.OK,Simple_Response(true,"Discount Added"))
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }

    get("discount/get_one_coupon_code") {
            val coupon_code = try {
                call.request.queryParameters["coupon_code"]
            }
            catch (e : Exception)
            {
                call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
                return@get
            }

            try {
                val coupon = discountDB.findDiscountByCouponCode(coupon_code!!)
                call.respond(HttpStatusCode.OK,coupon!!)

            }
            catch (e : Exception)
            {
                call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
            }
    }

    get("discount/getAll") {
        try {
            val discount = discountDB.getAllCouponCode()
            call.respond(HttpStatusCode.OK,discount)
        }
        catch (e:Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }

    delete("discount/delete") {
        val discount = try {
            call.request.queryParameters["coupon_code"]
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
            return@delete
        }

        try {
            val coupon_code = discountDB.deleteDiscount(discount!!)
            call.respond(HttpStatusCode.OK,Simple_Response(true,"Coupon Code Deleted"))
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }


}

