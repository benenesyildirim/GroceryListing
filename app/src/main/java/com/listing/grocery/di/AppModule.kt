package com.listing.grocery.di

import android.app.Application
import androidx.room.Room
import com.listing.grocery.common.Constants.BASE_URL
import com.listing.grocery.common.Constants.DB_NAME
import com.listing.grocery.data.ProductApi
import com.listing.grocery.data.local.ProductsDB
import com.listing.grocery.data.remote.repository.ProductRepositoryImpl
import com.listing.grocery.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): ProductApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductDatabase(app: Application): ProductsDB {
        return Room.databaseBuilder(app, ProductsDB::class.java, DB_NAME).build()
    }

    @Provides
    @Singleton
    fun provideProductRepository(api: ProductApi, db: ProductsDB): ProductRepository {
        return ProductRepositoryImpl(api, db.dao)
    }
}