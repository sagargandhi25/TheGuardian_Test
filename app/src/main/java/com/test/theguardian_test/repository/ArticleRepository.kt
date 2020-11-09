package com.test.theguardian_test.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.test.theguardian_test.database.ArticleDatabase
import com.test.theguardian_test.database.asDomainModel
import com.test.theguardian_test.domain.Article
import com.test.theguardian_test.network.APIServices
import com.test.theguardian_test.network.Constants
import com.test.theguardian_test.network.asDatabaseModel
import com.test.theguardian_test.utils.OpenForTesting
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

@OpenForTesting
class ArticleRepository (private val service: APIServices,private val database: ArticleDatabase) {

    suspend fun refreshArticle() {
        withContext(Dispatchers.IO) {
            Timber.d("Refresh Article is called")
            val articleList =  service.getArticleList(Constants.API_KEY,Constants.SHOW_FIELDS
                ,Constants.ORDERBY,Constants.PAGE).await()
            database.articleDao.insertAll(articleList.asDatabaseModel())
        }
    }

    val results:LiveData<List<Article>> = Transformations.map(database.articleDao.getLocalDBArticles()) {
        it.asDomainModel()
    }
}