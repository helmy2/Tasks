package com.example.tasks.di

import android.content.Context
import androidx.navigation.compose.rememberNavController
import com.example.tasks.data.remote.TasksApi
import com.example.tasks.data.repository.TaskRepoImpl
import com.example.tasks.data.repository.UserRepoImpl
import com.example.tasks.domain.repository.TaskRepo
import com.example.tasks.domain.repository.UserRepo
import com.example.tasks.domain.util.SessionManager
import com.example.tasks.domain.util.Constants.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGson() = Gson()

    @Singleton
    @Provides
    fun provideSessionManager(
        @ApplicationContext context: Context
    ) = SessionManager(context)

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val gson = GsonBuilder().setLenient().create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideTasksApi(retrofit: Retrofit): TasksApi =
        retrofit.create(TasksApi::class.java)

    @Singleton
    @Provides
    fun provideUserRepo(tasksApi: TasksApi, sessionManager: SessionManager): UserRepo =
        UserRepoImpl(tasksApi, sessionManager)

    @Singleton
    @Provides
    fun provideTaskRepo(tasksApi: TasksApi, sessionManager: SessionManager): TaskRepo =
        TaskRepoImpl(tasksApi, sessionManager)

}