package com.example

import com.example.authentications.JWT_Service
import com.example.authentications.hash
import com.example.data.model.Customer
import com.example.repository.*
import com.example.routes.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.sessions.*
import io.ktor.auth.*
import io.ktor.gson.*
import io.ktor.features.*
import io.ktor.locations.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads

fun Application.module(testing: Boolean = false) {

    Database_Factory.init()
    val customerDB = Customer_Repo()
    val adminDB = Admin_Repo()
    val accessDB = Accessories_Repo()
    val brandDB = Brand_Repo()
    val cartDB = Cart_Repo()
    val colorDB = Color_Repo()
    val discountDB = Discount_Repo()
    val imieDB = IMEI_NO_Repo()
    val invoiceDB = Invoice_Repo()
    val mobileDB = Mobile_Repo()
    val orderDb = Order_Repo()
    val paymentDB = Payment_Repo()
    val pincodeDB = Pincode_Repo()
    val productDB = Product_Repo()
    val purchaseDB = Purchase_Repo()
    val stockDB = Stock_Repo()
    val supplierDB = Supplier_Repo()
    val jwtService = JWT_Service()
    val hashFunction = {s : String -> hash(s) }

    install(Sessions) {
        cookie<MySession>("MY_SESSION") {
            cookie.extensions["SameSite"] = "lax"
        }
    }

    install(Authentication) {
    }

    install(Locations)

    install(ContentNegotiation) {
        gson {
        }
    }

    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        Customer_Route(customerDB,jwtService,hashFunction)
        Admin_Route(adminDB,jwtService,hashFunction)
        Product_Route(productDB)
        Colour_Route(colorDB)
        Brand_Route(brandDB)


        route("customers")
        {
            route("create")
            {
                // localhost:8007/mobile/create
                post {
                    val body = call.receive<String>()
                    call.respond(body)
                }
            }
            delete {
                val body = call.receive<String>()
                call.respond(body)
            }
        }
    }
}
data class MySession(val count: Int = 0)

