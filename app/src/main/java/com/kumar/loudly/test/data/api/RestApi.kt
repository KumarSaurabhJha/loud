package com.kumar.loudly.test.data.api

import com.kumar.loudly.test.data.model.GithubApiDataHolder
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("search/repositories")
    suspend fun getGitHubApis(
        @Query("q") apiName: String
    ): GithubApiDataHolder
}