package com.capsule.android.healthylifestyle.dagger

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

@InstallIn(ApplicationComponent::class)
@Module
class SchedulersModule {

    @Provides
    fun provideMainThread(): Scheduler = AndroidSchedulers.mainThread()
}