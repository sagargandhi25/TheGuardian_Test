package com.test.theguardian_test.di

import com.test.theguardian_test.viewmodel.ArticleViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule= module {
    viewModel { ArticleViewModel(get()) }
}