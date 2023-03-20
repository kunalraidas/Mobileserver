package com.example.routes

import com.example.data.model.Product
import com.example.data.response.Simple_Response
import com.example.repository.Product_Repo
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import java.io.File

fun Route.Product_Route(
    productDB : Product_Repo
){
    // Add Product
    post("product/add") {
        val add_product = try {
            call.receive<Product>()
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
            return@post
        }
        try {
            /*val product = Product(addproduct.product_id,addproduct.product_name,
            addproduct.product_desc,addproduct.cate_name,addproduct.color,addproduct.brand_id,
            Mobile = addproduct.Mobile, Accessories = addproduct.Accessories) */
            productDB.addproduct(add_product)
            call.respond(HttpStatusCode.OK,Simple_Response(true,"Product Added"))
            return@post
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.OK,Simple_Response(false,"${e.message}"))
        }

        // Product Is Already in database
        val exist = productDB.productExists(add_product.product_id)
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
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
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

    // Get One Mobile Details
    get("product/getOneMobile") {
        val mobile_id = try {
            call.request.queryParameters["id"]?.toInt()
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
            return@get
        }

        try {
            val mobile = productDB.getMobileProductByMobileId(mobile_id!!)
            call.respond(HttpStatusCode.OK,mobile!!)
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }

    // Get One Accessories Details
    get("product/getOneAccessories") {
        val access_id =  try {
            call.request.queryParameters["id"]?.toInt()
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
            return@get
        }

        try {
            val access = productDB.getAccessoriesProductByAccessId(access_id!!)
            call.respond(HttpStatusCode.OK,access!!)
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }

    // Get One product details
    get("product/getOneProduct") {
        val id = try {
            call.request.queryParameters["id"]?.toInt()
        }
        catch (e:Exception)
        {
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"Enter Product Id"))
            return@get
        }
        try {
            val productId = productDB.getOneProduct(id!!)
            call.respond(HttpStatusCode.OK,productId!!)
        }
        catch (e : Exception)
        {
                call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }

    // Get all Mobile Product in database
    get("product/getAllMobile") {
        try {
            val product = productDB.getAllMobile()
            call.respond(HttpStatusCode.OK,product)
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }

    // Get All Accessories in database
    get("product/getAllAccessories") {
        try {
            val access = productDB.getAllAccessories()
            call.respond(HttpStatusCode.OK,access)
        }
        catch (e : Exception)
        {
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
        }
    }

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
                call.request.queryParameters["id"]?.toInt()
            }
            catch (e : Exception)
            {
                call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"${e.message}"))
                return@delete
            }
            try {
                     val product = productDB.deleteProduct(productId!!)
                     //      call.respond(HttpStatusCode.OK,product)
                     call.respond(HttpStatusCode.OK,Simple_Response(true,"Product Deleted successfully"))
            }
             catch (e : Exception)
             {
                    call.respond(HttpStatusCode.Conflict,Simple_Response(false,"${e.message}"))
             }
     }

    post("profileImage/upload") {
        val data = try {
            call.receiveMultipart()
        }catch (e : Exception){
            call.respond(HttpStatusCode.BadRequest,Simple_Response(false,"Image not provided"))
            return@post
        }

        try {
            data.forEachPart {
                if(it is PartData.FileItem && it.contentType!=null){

                    val fileName = "image${System.currentTimeMillis()}.${it.contentType?.contentSubtype}"
                    val path = "src/storage/images/"+fileName
                    val file = File(path)
                    val fileBytes = it.streamProvider().readBytes()
                    file.writeBytes(fileBytes)
                    call.respond(HttpStatusCode.OK,Simple_Response(true,"http://localhost:8007/storage/images/$fileName"))
                }
            }

        }catch (e : Exception){
            call.respond(HttpStatusCode.Conflict,Simple_Response(false,"$e"))
        }
    }


}




