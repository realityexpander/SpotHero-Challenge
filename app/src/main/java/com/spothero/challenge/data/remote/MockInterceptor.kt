package com.spothero.challenge.data.remote

import android.content.Context
import com.spothero.challenge.BuildConfig
import com.spothero.challenge.data.model.SpotDTO
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException
import javax.inject.Inject

/**
 * This will help us to test our networking code while a particular API is not implemented
 * yet on Backend side.
 */
const val SUCCESS_CODE = 200
const val FAILURE_CODE = 500

class MockInterceptor @Inject constructor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        if (BuildConfig.DEBUG) {
            val uri = chain.request().url.toUri().toString()
            val isNumberOnEnd = uri.endsWithNumber("spots/")

            // get mock data from local asset file
            val responseString = when {
                uri.endsWith("spots") -> getSpotsJsonFromAsset(context)
                isNumberOnEnd -> getSpotIdJsonFromAsset(uri.substringAfterLast("spots/"), context)
                else -> getJsonAssetFile("error.json", context)
                // else -> return chain.proceed(chain.request()) // use a live request if not mocked
            }

            return chain.proceed(chain.request())
                .newBuilder()
                .code(SUCCESS_CODE)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    responseString.toByteArray()
                        .toResponseBody(("application/json").toMediaType())
                )
                .addHeader("content-type", "application/json")
                .build()
        } else {
            //just to be on safe side.
            throw IllegalAccessError(
                "MockInterceptor is only meant for Testing Purposes and " +
                        "bound to be used only with DEBUG mode"
            )
        }
    }
}

private fun String.endsWithNumber(pathWithFinalSlash: String): Boolean {
    val numberStr = this.substringAfterLast(pathWithFinalSlash)

    if (numberStr.isNotEmpty()) {
        val number = numberStr.toIntOrNull()
        if (number != null) {
            return true
        }
    }

    return false
}

fun getSpotsJsonFromAsset(context: Context): String {
    return getJsonAssetFile("spots.json", context)
}

fun getSpotIdJsonFromAsset(spotId: String, context: Context): String {
    val spotsJson = getSpotsJsonFromAsset(context)

    return getSpotId(spotsJson, spotId)
}

private fun getSpotId(spotsJson: String, spotId: String): String {
    return Json.decodeFromString<List<SpotDTO>>(spotsJson)
        .find { it.id.toString() == spotId }
        ?.let { Json.encodeToString(it) }
        ?: throw IllegalArgumentException("No spot with id $spotId found")
}

fun getJsonAssetFile(assetsFile: String, context: Context): String {
    return try {
        val inputStream = context.assets.open("coins.json")
        val buffer = ByteArray(inputStream.available())
        inputStream.read(buffer)
        inputStream.close()

        String(buffer)
    } catch (e: IOException) {
        e.printStackTrace()

        "[]" // default return an empty array
    }
}

fun main() {
    testEndsWithNumber()
    testGetSpotId()
}

fun testEndsWithNumber() {
    val uri = "https://api.spothero.com/spots/1"
    println(uri.endsWithNumber("spots/"))
    println(uri.substringAfterLast("spots/"))
}

fun testGetSpotId() {
    val spots = """
    [
        {
            "id": 1,
            "address": {
                "street": "127 N Dearborn St.",
                "city": "Chicago",
                "state": "IL"
            },
            "description": "Enter this location at 127 N Dearborn St. This is the Block 37 parking garage operated by SP+. It is located on the east/right-hand side of N Dearborn St (a one-way street) between W Washington St. & W Randolph St. Once you pass the 'Washington CTA Blue Line Station' entrance, the garage will be 100 feet ahead on the right side. You must drive right up to the garage door for it to open. **In order to park at this facility you must be a patron of Block 37",
            "distance": "0.1 mi",
            "facility_photo": "facility_photo_1.jpg",
            "price": 1400
        },
        {
            "id": 2,
            "address": {
                "street": "181 N Clark St.",
                "city": "Chicago",
                "state": "IL"
            },
            "description": "Enter this location at 181 N Clark St. This is the Government Center garage operated by InterPark. It is located on the east/left-hand side of N Clark St. (a one-way street) between W Lake St. and W Randolph St. You may also enter this location at its other entrance, 81 W Lake St.",
            "distance": "390 ft",
            "facility_photo": "facility_photo_2.jpg",
            "price": 1650
        }
    ]
    """.trimIndent()

    println(getSpotId(spots, "2"))
    println(getSpotId(spots, "1"))
    println(getSpotId(spots, "3")) // should throw IllegalArgumentException

}