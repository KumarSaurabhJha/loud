package com.kumar.loudly.test.data.model

data class GithubApiDataHolder(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)