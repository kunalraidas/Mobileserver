package com.example.routes


import com.example.data.model.Supplier
import com.example.data.response.Simple_Response
import com.example.repository.Supplier_Repo
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Route.Supplier_Route(
    supplierDB : Supplier_Repo
)
{
    post("supplier/add") {
        val supplier = try {
            call.receive<Supplier>()
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,e.message ?: "Missing some Fields"))
            return@post
        }

        try {
            val supplier_add = Supplier(
                supplier.supplier_email,supplier.supplier_name,
                supplier.company_name,supplier.address,
                supplier.contact_no
            )
            supplierDB.addSupplier(supplier_add)
            call.respond(HttpStatusCode.OK,Simple_Response(true,"Data Added Successfully"))
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,e.message?: "Some Problem Occur"))
        }

        // If the Supplier Is Already Exist in database
        val exists = supplierDB.supplierExist(supplier.supplier_email)

        if (exists)
        {
            call.respond(HttpStatusCode.OK,Simple_Response(false,"This Email is Already Exists in the database"))
            return@post
        }
    }

    // Get One Supplier Details
    get("supplier/getOne") {
        val email = try {
            call.request.queryParameters["email"]
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"Enter Proper Email id "))
            return@get
        }

        try {
            val supplierData = supplierDB.findSupplierByEmail(email!!)
            call.respond(HttpStatusCode.OK,supplierData)
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.NotFound)
        }
    }

    // Get all Supplier Details
    get("customer/getAll") {
        try {
            val supplierAll =  supplierDB.getAllSupplier()
            call.respond(HttpStatusCode.OK,supplierAll)
        }
        catch(e : Exception)
        {
            call.respond(HttpStatusCode.NoContent,e.message.toString())
        }
    }


}
