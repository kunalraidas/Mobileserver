package com.example.authentications

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.example.data.model.Admin
import com.example.data.model.Customer

class JWT_Service
{
        private  val issuer = "Mobile_Server"
        private val jwtSecrate = System.getenv("JWT_SECRATE")
        private val algorithm = Algorithm.HMAC512(jwtSecrate)

    val verifier : JWTVerifier = JWT.require(algorithm)
        .withIssuer(issuer).build()

    fun generateCustomerToken(customer: Customer):String
    {
        return JWT.create().withSubject("CustomerAuthentication")
            .withIssuer(issuer)
            .withClaim("email",customer.email)
            .sign(algorithm)
    }

    fun generateAdminToken(admin: Admin):String
    {
        return JWT.create().withSubject("AdminAuthentication")
            .withIssuer(issuer).withClaim("Admin_email",admin.admin_email)
            .sign(algorithm)
    }

}