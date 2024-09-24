package com.larkes.firebasecryptoportfoliosample.platform

import com.larkes.firebasecryptoportfoliosample.core.coreModule
import com.larkes.firebasecryptoportfoliosample.features.auth.authModule
import com.larkes.firebasecryptoportfoliosample.features.portfolio.portfolioModule
import com.larkes.firebasecryptoportfoliosample.utils.Inject
import org.koin.core.context.startKoin
import org.koin.dsl.module

object PlatformSDK {

    fun init(configuration: PlatformConfiguration) {
        startKoin {
            modules(module {
                single { configuration }
            })
            modules(coreModule)
            modules(authModule)
            modules(portfolioModule)
        }
        Inject.saveConfiguration(configuration)
    }

}