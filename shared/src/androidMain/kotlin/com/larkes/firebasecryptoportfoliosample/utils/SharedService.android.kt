package com.larkes.firebasecryptoportfoliosample.utils

import java.util.UUID

actual object SharedService {
    actual fun generateUniqId():String{
        return UUID.randomUUID().toString()
    }
}