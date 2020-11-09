package com.test.theguardian_test.background

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.test.theguardian_test.repository.ArticleRepository
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber
import java.lang.Exception

/**
 * WorkManager automatically calls Worker.doWork() on a background thread.
 * Periodically syncing application data with a server
 */
class SyncDatabaseWM (appContext: Context, params: WorkerParameters): CoroutineWorker(appContext,params),KoinComponent {

    val dataSyncRepository: ArticleRepository by inject()

    companion object {
        const val WORK_NAME = "com.test.theguardian_test.background.SyncDatabaseWM"
    }
    override suspend fun doWork(): Result {
        /**
         * Sync the backend API data with local database even if user is not using the app or device restarts
         */
        try{
            dataSyncRepository.refreshArticle()
            Timber.d("Workmanager: sync in progress")
        }
        catch (e: Exception) {
            Timber.e("Workmanager error: ${e.localizedMessage}")
            return Result.retry()
        }
        return Result.success()
    }

}