package com.example.routes

import com.example.authentications.JWT_Service
import com.example.data.model.Customer
import com.example.data.modals.Login_Request
import com.example.data.modals.Registration_Request
import com.example.data.response.Simple_Response
import com.example.repository.Customer_Repo
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


const val API_VERSION = "/v1"
const val CUSTOMER = "$API_VERSION/customer"
const val REGISTER_REQUEST = "$CUSTOMER/register"
const val LOGIN_REQUEST = "$CUSTOMER/login"


@Location(REGISTER_REQUEST)
class UserRegisterRoute()

@Location(LOGIN_REQUEST)
class UserLoginRoute()


fun Route.Customer_Route(
    custDb : Customer_Repo,
    jwtService: JWT_Service,
    hashFunction: (String) -> String
){
        post<UserRegisterRoute> {

            // Customer is Register
            val registerRequest =
                try
                {
                    call.receive<Registration_Request>()
                }
                catch (e:Exception)
                {
                    call.respond(HttpStatusCode.BadRequest,Simple_Response(false,e.message ?: "Missing Some Filed"))
                    return@post
                }

            try {
                val customer = Customer(registerRequest.email,hashFunction(registerRequest.password)
                    ,registerRequest.first_name,registerRequest.last_name,
                    registerRequest.phone_no,registerRequest.cust_address,registerRequest.delivery_address,
                    registerRequest.pincode)
                custDb.addCustomer(customer)
                call.respond(HttpStatusCode.OK,Simple_Response(true,"Registration Successfully"))
            }
            catch (e:Exception)
            {
                call.respond(HttpStatusCode.Conflict,Simple_Response(false,e.message ?: "Some Problem Occur"))
            }

            // Customer is already Exists
            val exists = custDb.customerExists(registerRequest.email)
            if (exists)
            {
                call.respond(HttpStatusCode.OK,Simple_Response(false,"This Email Id Is Already Registered"))
                return@post
            }
        }

        post<UserLoginRoute> {

            // Customer Login
            val loginRequest =
                try {
                    call.receive<Login_Request>()
                }
                catch (e:Exception)
                {
                    call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"Missing Some Filed"))
                    return@post
                }
            try {
                val customer = custDb.findCustomerByEmail(loginRequest.email)

                if (customer == null)
                {
                    call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"Wrong Email Id"))
                }
                else
                {
                    if (customer.password == hashFunction(loginRequest.password))
                    {
                        call.respond(HttpStatusCode.OK,Simple_Response(true,"Login Successfully"))
                        // jwtService.generateCustomerToken(customer)
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

       // Update Customer Profile
        post("customer/update") {
            val updateRequest = try {
                call.receive<Customer>()
            }
            catch (e:Exception)
            {
                call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"Improper User Data"))
                return@post
            }

            try {
                custDb.updateCustomer(updateRequest)
                call.respond(HttpStatusCode.OK,Simple_Response(true,"Profile Updated"))
            }
            catch (e : Exception)
            {
                call.respond(HttpStatusCode.Conflict,Simple_Response(false,"$e"))
            }
        }


       // Get All Customer Detail
        get("customer/getAll") {
             try {
                    val l = custDb.getAllCustomer()
                    call.respond(HttpStatusCode.OK,l)
             }
             catch (e : Exception)
             {
                call.respond(HttpStatusCode.NoContent,e.message.toString())
             }
        }

    // Get one  Customer Details
    get ("customer/get"){
        val email = try {
            call.request.queryParameters["email"]
        }
        catch (e : Exception) {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"Provide Email"))
            return@get
        }
        try {
            val u = custDb.findCustomerByEmail(email!!)
            call.respond(HttpStatusCode.OK,u!!)
        }
        catch (e : Exception) {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }

}



