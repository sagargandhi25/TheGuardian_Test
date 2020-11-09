package com.test.theguardian_test.di


import com.test.theguardian_test.database.ArticleDatabase
import com.test.theguardian_test.network.APIServices
import com.test.theguardian_test.repository.ArticleRepository
import org.koin.dsl.module

val repositoryModule = module {
    fun provideRepository(api: APIServices, dao: ArticleDatabase): ArticleRepository {
        return ArticleRepository(api, dao)
    }

    single {
        provideRepository(get(), get())
    }
}