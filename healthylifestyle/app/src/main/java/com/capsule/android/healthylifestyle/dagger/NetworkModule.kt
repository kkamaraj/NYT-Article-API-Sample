package com.capsule.android.healthylifestyle.dagger

import com.capsule.android.healthylifestyle.api.NewyorkTimesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideNYTService(): NewyorkTimesService {
        return NewyorkTimesService.create()
    }
}
