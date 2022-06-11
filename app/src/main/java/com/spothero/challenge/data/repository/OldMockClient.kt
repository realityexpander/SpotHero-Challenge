package com.spothero.challenge.data.repository
//
//import android.content.Context
//import android.util.Log
//import okhttp3.*
//import okhttp3.MediaType.Companion.toMediaTypeOrNull
//import okhttp3.ResponseBody.Companion.toResponseBody
//import okio.internal.commonAsUtf8ToByteArray
//import java.io.ByteArrayOutputStream
//import java.io.IOException
//import java.io.InputStream
//import java.lang.Exception
//
//class MockClient(private val context: Context) : Interceptor {
//
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val endpoint = chain.request().url.encodedPathSegments.last()
//        var response = ""
//        val responseBuilder = Response.Builder()
//            .protocol(Protocol.HTTP_1_1)
//            .request(chain.request())
//
//        Log.d("URL", chain.request().url.toString())
//
//        try {
//            response = context.resources.assets.open("$endpoint.json").parseToString()
//
//            responseBuilder.code(200)
//                .body(response.commonAsUtf8ToByteArray().toResponseBody("application/json".toMediaTypeOrNull()))
//                .addHeader("content-type", "application/json")
//        } catch (e: Exception) {
//            response = "[]"
//
//            responseBuilder.code(400)
//                .body(response.commonAsUtf8ToByteArray().toResponseBody("application/json".toMediaTypeOrNull()))
//                .addHeader("content-type", "application/json")
//        }
//
//        return responseBuilder.message(response).build()
//    }
//}
//
//private fun InputStream.parseToString(): String = this.let {
//    val outputStream = ByteArrayOutputStream()
//
//    val buffer = ByteArray(1024)
//    var length: Int
//    try {
//        while (this.read(buffer).also { length = it } != -1) {
//            outputStream.write(buffer, 0, length)
//        }
//        outputStream.close()
//        this.close()
//    } catch (e: IOException) {
//        return@let ""
//    }
//
//    outputStream.toString()
//}