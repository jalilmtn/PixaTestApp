package com.example.database.di

import android.app.Application
import com.example.database.data.ImageDatabaseRepo
import com.example.database.data.ImageDatabaseRepoImpl
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
    fun provideImageRepo(driver: SqlDriver): ImageDatabaseRepo {
        return ImageDatabaseRepoImpl(ImageDatabase(driver).imageEntitiyQueries)
    }

}