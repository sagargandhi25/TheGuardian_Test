package com.test.theguardian_test.di

import android.app.Application
import androidx.room.Room
import com.test.theguardian_test.database.ArticleDatabase


import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

//Module: returns instance of the class (loose coupling)
val databaseModule = module {
    fun providesDatabase(application: Application): ArticleDatabase {
        return Room.databaseBuilder(application, ArticleDatabase::class.java, "articles.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    // singleton: single instance
    single { providesDatabase(androidApplication()) } // lifecycle of the application
}