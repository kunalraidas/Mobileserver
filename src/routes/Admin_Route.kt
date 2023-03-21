package com.example.routes

import com.example.authentications.JWT_Service
import com.example.data.modals.Admin_Login_Request
import com.example.data.modals.Admin_Register_Request
import com.example.data.model.Admin
import com.example.data.response.Simple_Response
import com.example.repository.Admin_Repo
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

const val ADMIN_API = "/v1"
const val ADMIN = "$ADMIN_API/admin"
const val ADMIN_REGISTER = "$ADMIN/register"
const val ADMIN_LOGIN = "$ADMIN/login"

@Location(ADMIN_REGISTER)
class AdminRegisterRoute()

@Location(ADMIN_LOGIN)
class AdminLoginRoute()

fun Route.Admin_Route(
    admindb : Admin_Repo,
    jwtService: JWT_Service,
    hashFunction : (String) -> String
){
    post<AdminRegisterRoute> {
        val RegisterRequest =
            try {
                call.receive<Admin_Register_Request>()
            }
            catch (e:Exception)
            {
                call.respond(HttpStatusCode.BadRequest,Simple_Response(false,e.message ?:"some Filed Missing"))
                return@post
            }

        try {
            val admin = Admin(RegisterRequest.adminName,
                RegisterRequest.adminEmail,
                hashFunction(RegisterRequest.adminPassword))
            admindb.addAdmin(admin)
            call.respond(HttpStatusCode.OK,Simple_Response(true,jwtService.generateAdminToken(admin)))
        }
        catch (e:Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,e.message ?: "some Problem Occur"))
        }
    }

    post<AdminLoginRoute> {
        val LoginRequest =
            try {
                call.receive<Admin_Login_Request>()
            }
            catch (e:Exception)
            {
                call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"Missing Some Fields"))
                return@post
            }
        try {
            val admin = admindb.findAdminByEmail(LoginRequest.adminEmail)

            if (admin == null)
            {
                call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"Wrong Email Id"))
            }
            else
            {
                if (admin.admin_password == hashFunction(LoginRequest.adminPassword))
                {
                    call.respond(HttpStatusCode.OK,Simple_Response(true,"Login Successfully"))
                    // jwtService.generateAdminToken(admin)
                }
                else
                {
                    call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"Wrong Password"))
                }
            }
        }
        catch (e:Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,e.message ?: "Some Problem Occur"))
        }
    }

    get("admin/get")  {
        val email = try {
            call.request.queryParameters["email"]
        }
        catch (e : Exception) {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"Please provide a email"))
            return@get
        }
        try {
            val a = admindb.findAdminByEmail(email!!)
            call.respond(HttpStatusCode.OK,a!!)
        }
        catch (e : Exception) {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }


    //    get ("customer/get"){
//    val email = try {
//        call.request.queryParameters["email"]
//    }
//    catch (e : Exception) {
//        call.respond(HttpStatusCode.BadRequest, Simple_Response(false,"Provide Email"))
//        return@get
//    }
//    try {
//        val u = custDb.findCustomerByEmail(email!!)
//        call.respond(HttpStatusCode.OK,u!!)
//    }
//    catch (e : Exception) {
//        call.respond(HttpStatusCode.Conflict, Simple_Response(false,"${e.message}"))
//    }

}