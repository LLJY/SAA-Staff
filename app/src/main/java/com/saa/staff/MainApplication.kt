package com.saa.staff

import android.app.Application
import android.app.ProgressDialog
import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.saa.staff.activities.mainui.ManageCourses.ManageCourseRepository
import com.saa.staff.activities.mainui.ManageDiploma.ManageDiplomaRepository
import com.saa.staff.activities.mainui.ManageFellowship.ManageFellowshipRepository
import com.saa.staff.interfaces.FirebaseCloudService
import com.saa.staff.repositories.LoginRepository
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
        pd.setTitle("Loading...")
        return pd
    }
}
@Module
@InstallIn(ApplicationComponent::class)
object RepositoriesModule {
    @Provides
    @Singleton
    fun provideLoginRepository(retrofit: FirebaseCloudService): LoginRepository{
        return LoginRepository(retrofit)
    }
    @Provides
    @Singleton
    fun provideManageCourseRepository(retrofit: FirebaseCloudService): ManageCourseRepository{
        return ManageCourseRepository(retrofit)
    }
    @Provides
    @Singleton
    fun provideManageFellowshipRepository(retrofit: FirebaseCloudService): ManageFellowshipRepository{
        return ManageFellowshipRepository(retrofit)
    }
    @Provides
    @Singleton
    fun provideManageDiplomaRepository(retrofit: FirebaseCloudService): ManageDiplomaRepository{
        return ManageDiplomaRepository(retrofit)
    }
}
@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule{
    @Provides
    fun provideRetrofit(): Retrofit{
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.94:8080/")
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .build()
        return retrofit
    }
    @Provides
    @Singleton
    fun provideFirebaseCloudService(retrofit: Retrofit): FirebaseCloudService{
        return retrofit.create(FirebaseCloudService::class.java)
    }

}
@HiltAndroidApp
class MainApplication: Application() {
}