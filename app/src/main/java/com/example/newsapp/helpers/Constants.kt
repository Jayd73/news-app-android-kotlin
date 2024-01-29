package com.example.newsapp.helpers

object Constants {
    const val API_KEY = "674a206d51784fba8b9974274154ec1e"
    const val BASE_URL = "https://newsapi.org/v2"
    const val TOP_HEADLINES_ENDPOINT = "$BASE_URL/top-headlines"
    const val PAGE_KEY = "page"
    const val PAGE_SIZE_KEY = "pageSize"
    const val COUNTRY_KEY = "country"
    const val CATEGORY_KEY = "category"
    const val ARTICLE_FETCH_COUNT = 20
    const val MAX_ARTICLES_IN_MEMORY = 60
    const val MAX_HEADLINE_SIZE = 100
    const val MAX_DESCRIPTION_SIZE = 140

    //Country codes
    const val US = "us"
    const val IND = "in"
    const val GB = "gb"

    //Categories
    const val SCIENCE = "science"
}