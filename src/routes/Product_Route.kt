package com.example.routes

import com.example.data.model.Product
import com.example.data.response.Simple_Response
import com.example.repository.Product_Repo
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.Product_Route(
    productDB : Product_Repo

){
    // Add Product
    post("product/add") {
        val addproduct = try {
            call.receive<Product>()
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
            return@post
        }

        try {
            val product = Product(addproduct.product_id,addproduct.product_name,
            addproduct.product_desc,addproduct.cate_name,addproduct.color,addproduct.brand_id)
            productDB.addproduct(product)
            call.respond(HttpStatusCode.OK,Simple_Response(true,"Product Added"))
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }

        // Product Is Already in database
        val exist = productDB.productExists(addproduct.product_id)
        if (exist)
        {
            call.respond(HttpStatusCode.OK,Simple_Response(false,"This product is already in the database"))
            return@post
        }
    }

    // Update Product Details
    post("product/update") {
        val productUpdate = try {
            call.receive<Product>()
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"Proper data are not insert in table"))
            return@post
        }
        try {
           productDB.updateProduct(productUpdate)
           call.respond(HttpStatusCode.OK,Simple_Response(false,"Product Update"))
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }

    // get One product details
    get("product/getOneProduct") {
        val id = try {
            call.request.queryParameters["id"]
        }
        catch (e:Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"Enter Product Id"))
            return@get
        }

        try {
           // val productId = productDB.getOneProduct()

        }
        catch (e : Exception)
        {

        }
    }

//    // Get one  Customer Details

//
//        try {
//            val u = custDb.findCustomerByEmail(email!!)
//            call.respond(HttpStatusCode.OK,u!!)
//        }
//        catch (e : Exception)
//        {
//            call.respond(HttpStatusCode.NotFound)
//        }
//    }


    // Get All Product in database
    get("product/getAllProduct") {
        try {
            val product = productDB.getAllProduct()
            call.respond(HttpStatusCode.OK,product)
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.NoContent,e.message.toString())
        }

    }

    // Delete Product Details
    delete("product/delete") {
            val productId = try {
                call.receive<Product>()
            }
            catch (e : Exception)
            {
                call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"This Product id is not present in database"))
                return@delete
            }

        try {
            productDB.deleteProduct(productId)
            call.respond(HttpStatusCode.OK,Simple_Response(true,"Product Deleted successfully"))
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"Some Problem Occur"))
        }
    }

}




