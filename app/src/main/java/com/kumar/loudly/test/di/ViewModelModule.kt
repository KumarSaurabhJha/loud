package com.kumar.loudly.test.di

import com.kumar.loudly.test.presentation.viewmodel.GithubRepositoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { GithubRepositoryViewModel(get()) }
}