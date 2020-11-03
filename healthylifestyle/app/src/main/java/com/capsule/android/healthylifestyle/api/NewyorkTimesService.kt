package com.capsule.android.healthylifestyle.api

import com.capsule.android.healthylifestyle.model.ArticleResponse
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewyorkTimesService {

    @GET("search/v2/articlesearch.json")
    fun searchArticles(
        @Query("fq") fq: String,
        @Query("api-key") api_key: String
    ): Single<ArticleResponse>

    companion object {
        private const val BASE_URL = "https://api.nytimes.com/svc/"

        fun create(): NewyorkTimesService {
            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(NewyorkTimesService::class.java)
        }
    }
}