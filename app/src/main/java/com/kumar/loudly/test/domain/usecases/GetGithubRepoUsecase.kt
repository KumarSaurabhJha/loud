package com.kumar.loudly.test.domain.usecases

import com.kumar.loudly.test.data.repository.GithubApiRepository
import com.kumar.loudly.test.domain.UseCase
import com.kumar.loudly.test.domain.model.GithubRepositoryDomainModel

class GetGithubRepoUsecase(private val githubApiRepository: GithubApiRepository) :
    UseCase<String, List<GithubRepositoryDomainModel>> {


    override suspend fun execute(input: String): List<GithubRepositoryDomainModel> =
        githubApiRepository.getGitHubRepositories(input)
}