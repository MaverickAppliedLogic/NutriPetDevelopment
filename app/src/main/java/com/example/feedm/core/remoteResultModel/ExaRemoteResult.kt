package com.example.feedm.core.remoteResultModel

data class ExaRemoteResult(
    val requestId: String,
    var results: List<Result>
)