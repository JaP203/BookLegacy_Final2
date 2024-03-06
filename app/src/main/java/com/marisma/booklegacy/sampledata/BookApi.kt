package com.marisma.booklegacy.sampledata
import android.icu.text.CaseMap.Title
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.*
import java.io.IOException

class BookApi {

    private val client = OkHttpClient()
    private val gson = Gson()

    fun getBookById(id: String, callback: (Book?) -> Unit) {
        val url = "https://openlibrary.org/search.json?q=$id"
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(null)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.let {
                    val jsonString = it.string()
                    val book = parseJsonToBook(jsonString)
                    callback(book)
                } ?: run {
                    callback(null)
                }
            }
        })
    }

    private fun parseJsonToBook(jsonString: String): Book? {
        val jsonObject = gson.fromJson(jsonString, JsonObject::class.java)
        val docs = jsonObject.getAsJsonArray("docs")
        if (docs != null && docs.size() > 0) {
            val doc = docs.get(0).asJsonObject
            val title = doc.getAsJsonPrimitive("title")?.asString
            val photo = doc.getAsJsonPrimitive("cover_edition_key")?.asString

            val authorName = doc.getAsJsonArray("author_name")?.get(0)?.asString
            val numberOfPages = doc.getAsJsonPrimitive("number_of_pages_median")?.asInt
            val firstSentence = doc.getAsJsonArray("first_sentence")?.get(0)?.asString
            val genero = doc.getAsJsonArray("subject")?.get(0)?.asString

            // Construir y devolver el objeto Book
            return Book(title, numberOfPages ?: 0, authorName,  photo, false, firstSentence,genero)
        }
        return null
    }
}
