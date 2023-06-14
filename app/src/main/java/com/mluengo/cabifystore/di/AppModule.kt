package com.mluengo.cabifystore.di

import android.content.Context
import androidx.room.Room
import com.mluengo.cabifystore.BuildConfig
import com.mluengo.cabifystore.data.database.StoreDatabase
import com.mluengo.cabifystore.data.database.dao.ProductDao
import com.mluengo.cabifystore.data.source.network.RetrofitNetworkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val StoreBaseUrl: String = BuildConfig.BACKEND_URL

    @Provides
    @Singleton
    fun provideNetworkApi(okhttpCallFactory: Call.Factory): RetrofitNetworkApi {
        return Retrofit.Builder()
            .baseUrl(StoreBaseUrl)
            .callFactory(okhttpCallFactory)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(RetrofitNetworkApi::class.java)
    }

    @Provides
    @Singleton
    fun okHttpCallFactory(): Call.Factory = OkHttpClient.Builder()
        .build()

    @Provides
    @Singleton
    fun providesStoreDatabase(
        @ApplicationContext context: Context,
    ): StoreDatabase = Room.databaseBuilder(
        context,
        StoreDatabase::class.java, "cart_database")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideProductDao(
        database: StoreDatabase,
    ): ProductDao = database.productDao()
}