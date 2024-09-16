package com.larkes.firebasecryptoportfoliosample.features.portfolio

import com.larkes.firebasecryptoportfoliosample.features.portfolio.data.PortfolioRepositoryImpl
import com.larkes.firebasecryptoportfoliosample.features.portfolio.data.source.PortfolioFirebaseDataSource
import com.larkes.firebasecryptoportfoliosample.features.portfolio.data.source.PortfolioServerDataSource
import com.larkes.firebasecryptoportfoliosample.features.portfolio.domain.PortfolioRepository
import org.koin.dsl.module

val portfolioModule = module {
    factory { PortfolioServerDataSource() }
    factory { PortfolioFirebaseDataSource(get(), get()) }
    factory<PortfolioRepository> { PortfolioRepositoryImpl(get(), get()) }
}