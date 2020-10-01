package com.saa.staff

import android.app.Application
import android.app.ProgressDialog
import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.saa.staff.interfaces.FirebaseMessagingService
import com.saa.staff.interfaces.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object ProgressDialogModule {
    @Provides
    fun provideProgressDialog(@ActivityContext appContext: Context): ProgressDialog{
        // create pd and set some attributes
        val pd = ProgressDialog(appContext)
        pd.setCancelable(false)
        pd.setTitle("Please wait...")
        return pd
    }
}
@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule{
    @Provides
    @Singleton
    fun provideRetrofitService(): RetrofitService {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000")
            .addConverterFactory(Json {
                ignoreUnknownKeys = true
                isLenient = true
            }.asConverterFactory(MediaType.get("application/json")))
            .build()
        return retrofit.create(RetrofitService::class.java)
    }

    @Provides
    @Singleton
    fun provideFirebaseMessagingService(): FirebaseMessagingService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fcm.googleapis.com/fcm/")
            .addConverterFactory(Json {
                ignoreUnknownKeys = true
                isLenient = true
            }.asConverterFactory(MediaType.get("application/json")))
            .build()
        return retrofit.create(FirebaseMessagingService::class.java)
    }
}

@HiltAndroidApp
class MainApplication : Application()