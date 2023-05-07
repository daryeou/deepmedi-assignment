package com.daryeou.app.core.data.di

import com.daryeou.app.core.data.repository.DeepmediRepoImpl
import com.daryeou.app.core.domain.repository.DeepmediRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    @Singleton
    fun bindDeepmediRepository(
        deepmediRepo: DeepmediRepoImpl
    ): DeepmediRepo
}