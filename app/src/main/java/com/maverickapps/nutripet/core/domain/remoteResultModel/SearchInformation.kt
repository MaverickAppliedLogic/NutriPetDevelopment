package com.maverickapps.nutripet.core.domain.remoteResultModel

data class SearchInformation(
    val formattedSearchTime: String,
    val formattedTotalResults: String,
    val searchTime: Double,
    val totalResults: String
)