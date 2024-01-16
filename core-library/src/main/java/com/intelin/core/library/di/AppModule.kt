package com.intelin.core.library.di

import android.content.Context
import android.content.ContextWrapper
import com.google.gson.Gson
import com.intelin.core.library.repository.remote.PreferenceImpl
import com.intelin.core.library.encryption.RsaImpl
import com.intelin.core.library.repository.remote.SessionManagerImpl
import com.intelin.core.library.repository.interfaces.IPreference
import com.intelin.core.library.repository.interfaces.IRsa
import com.intelin.core.library.repository.interfaces.ISessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideContextWrapper(@ApplicationContext context: Context): ContextWrapper {
        return ContextWrapper(context)
    }

    @Provides
    @Singleton
    fun providesRSAEncrypt(contextWrapper: ContextWrapper): IRsa = RsaImpl(contextWrapper.assets)

    @Provides
    @Singleton
    fun providesPreference(@ApplicationContext context: Context): IPreference = PreferenceImpl(context)

    @Provides
    @Singleton
    fun providesSessionManager(preference: IPreference): ISessionManager = SessionManagerImpl(preference)

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()
}