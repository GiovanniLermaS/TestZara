package com.example.testkoombea.retrofit

import android.content.Context
import com.example.testkoombea.util.BASE_URL
import com.example.testkoombea.util.hasNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitClient {

    private val cacheSize = (5 * 1024 * 1024).toLong()

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply { level = HttpLoggingInterceptor.Level.BODY }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @ApplicationContext context: Context
    ): OkHttpClient =
        OkHttpClient.Builder()
            .cache(Cache(context.cacheDir, cacheSize))
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (hasNetwork(context)!!) {
                    val requestBuilder: Request.Builder = chain.request().newBuilder()
                    requestBuilder.header("Cache-Control", "public, max-age=" + 5)
                    requestBuilder.header("Content-Type", "application/json")
                    requestBuilder.header(
                        "Authorization",
                        "Bearer ab11cb7605a030ee350d08f805057413"
                    )
                    requestBuilder.build()
                } else
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()
                chain.proceed(request)
            }.build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)
}