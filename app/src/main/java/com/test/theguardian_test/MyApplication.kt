package com.test.theguardian_test

import android.app.Application
import androidx.work.*
import com.test.theguardian_test.background.SyncDatabaseWM
import com.test.theguardian_test.di.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MyApplication : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        /**
         * Start Koin
         */
        startKoin {
            androidContext(this@MyApplication)
            androidLogger(Level.NONE)
            modules(listOf(viewModelModule, repositoryModule, netModule, apiModule, databaseModule))
        }

        //Start WorkManager
        delayedInit()
    }

    private fun delayedInit() {
        applicationScope.launch {
            Timber.plant(Timber.DebugTree())
            //work performed by the worker
            setUpReccuringWork()
        }
    }

    /**
     *  Constraints in which your WM will run
     *  WM background job to fetch new network data daily
     */
    private fun setUpReccuringWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresCharging(false)
            .setRequiresBatteryNotLow(true)
            .build()

        val repeatingRequest = PeriodicWorkRequestBuilder<SyncDatabaseWM>(1,TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        Timber.d("WorkManager: Work is scheduled")
        WorkManager.getInstance().enqueueUniquePeriodicWork(
            SyncDatabaseWM.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )
    }
}