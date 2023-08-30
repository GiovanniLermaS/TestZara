package com.example.testzara.presentation.view.main

import com.example.testzara.data.repository.main.IMainActivityRepository
import com.example.testzara.data.repository.main.MainActivityRepository
import com.example.testzara.domain.character.GetCharacterUseCase
import com.example.testzara.domain.character.GetCharacterUseCaseImpl
import com.example.testzara.infrastructure.retrofit.ApiInterface
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

    @Provides
    fun provideCharacterUseCase(repository: IMainActivityRepository): GetCharacterUseCase {
        return GetCharacterUseCaseImpl(repository)
    }
}