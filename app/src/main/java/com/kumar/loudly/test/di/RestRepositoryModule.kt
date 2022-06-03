package com.kumar.loudly.test.di

import com.kumar.loudly.test.data.repository.GithubApiRepository
import org.koin.dsl.module

val restRepositoryModule = module {
    single {
        GithubApiRepository(get())
    }
}