package com.example.feedm.core.domain.remoteResultModel

data class ExaRemoteResult(
    val requestId: String,
    var results: List<Result>
)