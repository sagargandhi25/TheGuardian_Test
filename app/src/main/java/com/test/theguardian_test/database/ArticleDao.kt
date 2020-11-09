package com.test.theguardian_test.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArticleDao{

    @Query("SELECT * FROM DatabaseArticle")
    fun getLocalDBArticles():LiveData<List<DatabaseArticle>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(article: List<DatabaseArticle>)
}

@Database(entities = [DatabaseArticle::class],version = 1,exportSchema = false)
abstract class ArticleDatabase: RoomDatabase() {
    abstract val articleDao : ArticleDao
}