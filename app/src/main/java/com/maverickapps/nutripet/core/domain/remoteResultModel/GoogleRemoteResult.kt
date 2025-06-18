package com.maverickapps.nutripet.core.domain.remoteResultModel

data class GoogleRemoteResult(
    val context: Context,
    val items: List<Item>,
    val kind: String,
    val queries: Queries,
    val searchInformation: SearchInformation,
    val url: Url
)