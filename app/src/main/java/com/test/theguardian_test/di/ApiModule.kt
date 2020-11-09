package com.test.theguardian_test.di


import com.test.theguardian_test.network.APIServices
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    fun provideUserApi(retrofit: Retrofit): APIServices {
        return retrofit.create(APIServices::class.java)
    }

    single { provideUserApi(get()) }
}