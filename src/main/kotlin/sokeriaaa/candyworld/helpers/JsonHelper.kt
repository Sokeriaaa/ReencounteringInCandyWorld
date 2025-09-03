package sokeriaaa.candyworld.helpers

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object JsonHelper {

    val defaultGson: Gson by lazy {
        GsonBuilder().setPrettyPrinting().create()
    }

//    val json by lazy {
//        Json {
//            ignoreUnknownKeys = true
//            prettyPrint = true
//        }
//    }

    inline fun <reified T> encode(obj: T): String = defaultGson.toJson(obj)
    inline fun <reified T> decode(str: String): T = defaultGson.fromJson(str, T::class.java)

}