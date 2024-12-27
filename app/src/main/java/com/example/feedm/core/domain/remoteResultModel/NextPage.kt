package com.example.feedm.core.domain.remoteResultModel

data class NextPage(
    val count: Int,
    val cx: String,
    val inputEncoding: String,
    val outputEncoding: String,
    val safe: String,
    val searchTerms: String,
    val startIndex: Int,
    val title: String,
    val totalResults: String
)