package com.example.feedm.data.model

data class Item(
    val displayLink: String,
    val formattedUrl: String,
    val htmlFormattedUrl: String,
    val htmlSnippet: String,
    val htmlTitle: String,
    val kind: String,
    val link: String,
    val pagemap: Pagemap,
    val snippet: String,
    val title: String
)