package com.example.database.di

import android.app.Application
import com.example.database.data.ImageDataSource
import com.example.database.data.ImageDataSourceImpl
import com.example.database.data.ImageRepo
import com.example.database.data.ImageRepoImpl
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import imagedb.ImageDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    @Provides
    @Singleton
    fun provideSqlDriver(application: Application): SqlDriver {
        return AndroidSqliteDriver(
            schema = ImageDatabase.Schema,
            context = application,
            name = "image.db"
        )
    }

    @Provides
    @Singleton
    fun provideImageDataSource(driver: SqlDriver): ImageDataSource {
        return ImageDataSourceImpl(ImageDatabase(driver))
    }

    @Provides
    @Singleton
    fun provideImageRepo(source: ImageDataSource): ImageRepo {
        return ImageRepoImpl(source)
    }

}