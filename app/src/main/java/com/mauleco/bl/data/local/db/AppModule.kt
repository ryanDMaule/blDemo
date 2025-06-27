package com.mauleco.bl.di

import android.content.Context
import com.mauleco.bl.data.local.db.AppDatabase
import com.mauleco.bl.data.local.repo.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideRepository(
        db: AppDatabase,
        @ApplicationContext context: Context
    ): AppRepository {
        return AppRepository(db, context)
    }
}

