package com.capsule.android.healthylifestyle.model

import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    @field:SerializedName("status") val status: String,
    @field:SerializedName("copyright") val copyright: String,
    @field:SerializedName("response") val response: Docs
)

data class Docs(
    @field:SerializedName("docs") val articles: List<Article>
)

data class Article(
    @field:SerializedName("web_url") val webUrl: String,
    @field:SerializedName("snippet") val snippet: String,
    @field:SerializedName("headline") val headline: Headline
)

data class Headline(
    @field:SerializedName("main") val main: String,
    @field:SerializedName("print_headline") val printHeadline: String
)
