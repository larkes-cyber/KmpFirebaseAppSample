package com.larkes.firebasecryptoportfoliosample.core

import Constants.FIREBASE_DATABASE_URL
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.database.database
import org.koin.dsl.module

val coreModule = module {
    single { Firebase.auth }
    single {
        val database = Firebase.database(FIREBASE_DATABASE_URL)
        database.setPersistenceEnabled(true)
        database
    }
}