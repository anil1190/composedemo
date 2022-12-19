package com.neosoft.neostore.presentation.di

import com.neosoft.neostore.BuildConfig
import com.neosoft.neostore.data.remote.ApiInterface
import com.neosoft.neostore.data.remote.DBConstants
import com.neosoft.neostore.data.repositoryImpl.LoginRepositoryImpl
import com.neosoft.neostore.data.repositoryImpl.RegisterRepositoryImpl
import com.neosoft.neostore.domain.repository.LoginRepository
import com.neosoft.neostore.domain.repository.RegisterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideBaseUrl() = DBConstants.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG){
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }else{
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL:String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiInterface::class.java)

    @Provides
    @Singleton
    fun provideRegisterRepoImpl(registerRepositoryImpl: RegisterRepositoryImpl) : RegisterRepository = registerRepositoryImpl

    @Provides
    @Singleton
    fun provideLoginRepoImpl(loginRepositoryImpl: LoginRepositoryImpl) : LoginRepository = loginRepositoryImpl
}