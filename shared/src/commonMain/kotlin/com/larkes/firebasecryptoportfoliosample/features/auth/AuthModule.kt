package com.larkes.firebasecryptoportfoliosample.features.auth

import com.larkes.firebasecryptoportfoliosample.features.auth.data.AuthRepositoryImpl
import com.larkes.firebasecryptoportfoliosample.features.auth.data.source.AuthFirebaseDataSource
import com.larkes.firebasecryptoportfoliosample.features.auth.domain.AuthRepository
import org.koin.dsl.module

val authModule = module{
    factory { AuthFirebaseDataSource(get()) }
    factory<AuthRepository> { AuthRepositoryImpl(get()) }
}