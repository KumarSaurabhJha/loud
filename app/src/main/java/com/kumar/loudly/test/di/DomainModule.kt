package com.kumar.loudly.test.di

import com.kumar.loudly.test.domain.usecases.GetGithubRepoUsecase
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetGithubRepoUsecase(get())
    }
}