package com.larkes.firebasecryptoportfoliosample.utils


import platform.Foundation.NSUUID.Companion.UUID
import platform.UIKit.UIApplication
import platform.Foundation.NSString
import platform.Foundation.stringWithFormat


actual object SharedService {
    actual fun generateUniqId():String{
        return UUID().UUIDString()
    }

    actual fun formatFloat(num: Float): String {
        return NSString.stringWithFormat(format = "%.2f", num)
    }
}