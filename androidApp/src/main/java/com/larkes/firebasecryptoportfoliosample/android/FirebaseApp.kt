package com.larkes.firebasecryptoportfoliosample.android

import android.app.Application
import com.larkes.firebasecryptoportfoliosample.platform.PlatformConfiguration
import com.larkes.firebasecryptoportfoliosample.platform.PlatformSDK

class FirebaseApp:Application(){
    override fun onCreate() {
        super.onCreate()

        PlatformSDK.init(PlatformConfiguration(this))

    }
}