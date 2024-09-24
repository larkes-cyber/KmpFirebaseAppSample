package com.larkes.firebasecryptoportfoliosample.features.auth

import com.larkes.firebasecryptoportfoliosample.features.auth.data.AuthRepositoryImpl
import com.larkes.firebasecryptoportfoliosample.features.auth.data.source.AuthFirebaseDataSource
import com.larkes.firebasecryptoportfoliosample.features.auth.domain.AuthRepository
import com.larkes.firebasecryptoportfoliosample.features.auth.presentation.AuthViewModel
import com.larkes.firebasecryptoportfoliosample.features.portfolio.presentation.add.AddCoinViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module{
    factory { AuthFirebaseDataSource(get()) }
    factory<AuthRepository> { AuthRepositoryImpl(get()) }
    viewModel { AuthViewModel(get()) }
}