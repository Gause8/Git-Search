package com.example.viperdesignchallenge.data.network.response

data class SearchResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<Item>
)