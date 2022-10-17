package com.example

import com.example.authentications.JWT_Service
import com.example.authentications.hash
import com.example.repository.Customer_Repo
import com.example.repository.Database_Factory
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.sessions.*
import io.ktor.auth.*
import io.ktor.gson.*
import io.ktor.features.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads

fun Application.module(testing: Boolean = false) {

    Database_Factory.init()
    val db = Customer_Repo()
    val jwtService = JWT_Service()
    val hashFunction = {s : String -> hash(s) }

    install(Sessions) {
        cookie<MySession>("MY_SESSION") {
            cookie.extensions["SameSite"] = "lax"
        }
    }

    install(Authentication) {
    }

    install(ContentNegotiation) {
        gson {
        }
    }

    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("customer/{id}") {
            val id = call.parameters["id"]
            call.respond("$id")
        }

        get("customer") {
            val id = call.request.queryParameters["customer"]
            call.respond("$id")
        }

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

