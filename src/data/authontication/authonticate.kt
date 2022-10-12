package com.example.data.authontication

import io.ktor.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

private  val hashkey = System.getenv("HASH_SECRATE_KEY").toByteArray()
private  val hmacKey = SecretKeySpec(hashkey,"HmacSha1")


fun hash(password : String):String{
    val hmac = Mac.getInstance("HmacSha1")
    hmac.init(hmacKey)
    return  hex(hmac.doFinal(password.toByteArray(Charsets.UTF_8)))
}