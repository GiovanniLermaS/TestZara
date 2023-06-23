package com.example.testkoombea.view.main

import com.example.testkoombea.data.repository.main.IMainActivityRepository
import com.example.testkoombea.data.repository.main.MainActivityRepository
import com.example.testkoombea.retrofit.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.realm.kotlin.Realm

@Module
@InstallIn(ViewModelComponent::class)
class MainModule {

    @Provides
    fun provideAboutRepository(
        apiInterface: ApiInterface,
        realm: Realm
    ): IMainActivityRepository {
        return MainActivityRepository(apiInterface, realm)
    }
}