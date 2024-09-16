package com.larkes.firebasecryptoportfoliosample.utils

import platform.Foundation.NSUUID.Companion.UUID

actual object SharedService {
    actual fun generateUniqId():String{
        return UUID().UUIDString()
    }
}