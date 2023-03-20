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
import io.ktor.auth.jwt.*
import io.ktor.gson.*
import io.ktor.features.*
import io.ktor.http.content.*
import io.ktor.locations.*
import java.io.File

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads

fun Application.module(testing: Boolean = false) {

    Database_Factory.init()
    val customerDB = Customer_Repo()
    val adminDB = Admin_Repo()
    val brandDB = Brand_Repo()
    val cateDB = Category_Repo()
    val cartDB = Cart_Repo()
    val colorDB = Color_Repo()
    val discountDB = Discount_Repo()
    val imieDB = IMEI_NO_Repo()
    val orderDb = Order_Repo()
    val paymentDB = Payment_Repo()
    val pincodeDB = Pincode_Repo()
    val productDB = Product_Repo()
    val purchaseDB = Purchase_Repo()
    val supplierDB = Supplier_Repo()
    val jwtService = JWT_Service()
    val hashFunction = {s : String -> hash(s) }

    install(Sessions) {
        cookie<MySession>("MY_SESSION") {
            cookie.extensions["SameSite"] = "lax"
        }
    }

    install(Authentication) {
        jwt("jwt")
        {
            verifier(jwtService.verifier)
            realm = "MobileServer"
            validate {
                val payload = it.payload
                val email = payload.getClaim("email").asString()
                val customer = customerDB.findCustomerByEmail(email)
                customer
            }
        }
    }

    install(Locations)

    install(ContentNegotiation) {
        gson {
        }
    }

    routing {

        //for sending files to client
        static("storage/images"){
            staticRootFolder = File("src/storage/images")
            files(".")
        }
        static("/files"){
            staticRootFolder = File("src/storage/files")
            files(".")
        }
        static("/fonts"){
            staticRootFolder = File("src/storage/files/fonts")
            files(".")
        }
        static("/chat/images"){
            staticRootFolder = File("src/storage/chats/images")
            files(".")
        }
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        Admin_Route(adminDB,jwtService,hashFunction)
        Brand_Route(brandDB)
        Category_Route(cateDB)
        Customer_Route(customerDB,jwtService,hashFunction)
        Colour_Route(colorDB)
        Cart_Route(cartDB)
        Discount_Route(discountDB)
        IMEINO_Route(imieDB)
        Order_Route(orderDb)
        Product_Route(productDB)
        Purchase_Route(purchaseDB)
        Pincode_Route(pincodeDB)
        Payment_Route(paymentDB)
        Supplier_Route(supplierDB)


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

