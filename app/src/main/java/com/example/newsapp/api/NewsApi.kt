package com.example.newsapp.api

import android.util.Log
import com.example.newsapp.helpers.Constants
import com.example.newsapp.models.NewsApiResponse
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class NewsApi {
    suspend fun getArticles(page: Int, pageSize: Int): NewsApiResponse =
        withContext(Dispatchers.IO) {
            val endpoint = "${Constants.TOP_HEADLINES_ENDPOINT}" +
                    "?${Constants.PAGE_KEY}=$page" +
                    "&${Constants.PAGE_SIZE_KEY}=$pageSize" +
                    "&${Constants.COUNTRY_KEY}=${Constants.IND}" +
                    "&${Constants.CATEGORY_KEY}=${Constants.SCIENCE}"

            val openedConnection = URL(endpoint).openConnection() as HttpURLConnection
            openedConnection.requestMethod = "GET"
            openedConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:221.0) Gecko/20100101 Firefox/31.0")
            openedConnection.setRequestProperty("Content-Type","application/json")
            openedConnection.setRequestProperty("Accept","application/json")
            openedConnection.setRequestProperty("X-Api-Key", Constants.API_KEY)
            val reader = BufferedReader(InputStreamReader(openedConnection.inputStream))
            val response = reader.readText()
            val apiResponse = parseJson<NewsApiResponse>(response)
            reader.close()
            return@withContext apiResponse
        }

    private inline fun <reified T> parseJson(text: String): T =
        Gson().fromJson(text, T::class.java)
}



