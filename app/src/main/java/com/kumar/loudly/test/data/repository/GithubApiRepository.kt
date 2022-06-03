package com.kumar.loudly.test.data.repository

import com.kumar.loudly.test.data.api.RestApi
import com.kumar.loudly.test.data.model.GithubRepositoryDataHolder
import com.kumar.loudly.test.data.model.Item
import com.kumar.loudly.test.domain.model.GithubRepositoryDomainModel

class GithubApiRepository(private val restApi: RestApi) {

    suspend fun getGitHubRepositories(repoName: String) =
        processResponse(restApi.getGitHubApis(apiName = repoName))

    private fun processResponse(response: GithubRepositoryDataHolder): MutableList<GithubRepositoryDomainModel> {
        val list = mutableListOf<GithubRepositoryDomainModel>()

        response.items.forEach {
            list.add(it.toDomainModel())
        }
        return list
    }
}

fun Item.toDomainModel(): GithubRepositoryDomainModel =
    GithubRepositoryDomainModel(
        ownerName = this.owner.login,
        size = this.size.toString(),
        hasWiki = this.has_wiki,
        repoName = this.name
    )