package com.larkes.firebasecryptoportfoliosample.utils

import com.larkes.firebasecryptoportfoliosample.platform.PlatformConfiguration
import org.koin.core.Koin

object Inject {
    private var _configuration: PlatformConfiguration? = null

    val configuration: PlatformConfiguration
        get() = requireNotNull(_configuration)

    fun saveConfiguration(configuration: PlatformConfiguration) {
        _configuration = configuration
    }

}