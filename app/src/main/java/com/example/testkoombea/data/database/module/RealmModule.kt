package com.example.testkoombea.data.database.module

import com.example.testkoombea.model.Character
import com.example.testkoombea.model.Location
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RealmModule {
    @Provides
    @Singleton
    fun provideRealmInstance(): Realm {
        val config = RealmConfiguration
            .Builder(schema = setOf(Character::class, Location::class))
            .name("testkoombea.db")
            .schemaVersion(1)
            .build()
        return Realm.open(config)
    }
}