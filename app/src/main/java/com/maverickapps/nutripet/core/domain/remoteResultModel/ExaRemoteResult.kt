package com.maverickapps.nutripet.core.domain.remoteResultModel

data class ExaRemoteResult(
    val requestId: String,
    var results: List<Result>
)