package com.example.testelaniin.view.main

import com.example.testelaniin.data.repository.IMainActivityRepository
import com.example.testelaniin.data.repository.MainActivityRepository
import com.example.testelaniin.retrofit.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class MainModule {

    @Provides
    fun provideAboutRepository(apiInterface: ApiInterface): IMainActivityRepository {
        return MainActivityRepository(apiInterface)
    }
}