package com.test.theguardian_test.network

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServices {

    //https://content.guardianapis.com/search?api-key=1da140da-14a9-4b7c-8945-f50394e2fa44&show-fields=trailText,thumbnail&order-by=newest&page=5
    @GET(Constants.SEARCH)
    fun getArticleList(@Query("api-key") api_key: String?,@Query("show-fields",encoded = true) show_fields: String?,
                       @Query("order-by") order_by: String?,@Query("page") page: Int?)
            : Deferred<NetworkArticleContainer>

}