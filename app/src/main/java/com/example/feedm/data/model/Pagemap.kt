package com.example.feedm.data.model

data class Pagemap(
    val cse_image: List<CseImage>,
    val cse_thumbnail: List<CseThumbnail>,
    val hcard: List<Hcard>,
    val organization: List<Organization>
)