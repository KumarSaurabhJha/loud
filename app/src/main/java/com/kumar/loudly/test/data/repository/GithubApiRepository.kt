package com.kumar.loudly.test.data.repository

import com.kumar.loudly.test.data.api.RestApi
import com.kumar.loudly.test.data.model.GithubApiDataHolder

class GithubApiRepository(private val restApi: RestApi) {

    suspend fun getGitHubRepositories(repoName: String): GithubApiDataHolder {
        return restApi.getGitHubApis(apiName = repoName)
    }
}