package com.intelin.core.network.di

import com.intelin.core.library.repository.interfaces.IRsa
import com.intelin.core.library.repository.interfaces.ISessionManager
import com.intelin.core.network.BuildConfig
import com.intelin.core.network.remote.ApiService
import com.intelin.core.network.repository.interfaces.IServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        session: ISessionManager
    ): OkHttpClient {
        val client: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
        client.addInterceptor(
            Interceptor { chain ->
                val original = chain.request()

                val requestBuilder: Request.Builder = original.newBuilder()
                val start = System.currentTimeMillis()
                val token: String = runBlocking {
                    session.getAccessToken()
                }
                Timber.i("AccessToken lost ${System.currentTimeMillis() - start}")
                Timber.i("Header token $token")
                if (token.isNotEmpty()) {
                    requestBuilder.addHeader("token",  token)
                }
                val request: Request = requestBuilder.build()
                chain.proceed(request)
            }
        )
        client.addInterceptor(httpLoggingInterceptor)
        return client.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): IServiceApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IServiceApi::class.java)
    }

    @Singleton
    @Provides
    fun providesApi(service: IServiceApi, rsa: IRsa) = ApiService(service, rsa)

}