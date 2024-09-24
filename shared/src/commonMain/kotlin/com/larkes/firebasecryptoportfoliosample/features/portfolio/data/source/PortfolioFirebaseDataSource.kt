package com.larkes.firebasecryptoportfoliosample.features.portfolio.data.source

import Constants.FIREBASE_PORTFOLIO_COINS
import com.larkes.firebasecryptoportfoliosample.features.portfolio.data.models.PortfolioCoinEntity
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.database.FirebaseDatabase
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class PortfolioFirebaseDataSource(
    private val firebaseDatabase: FirebaseDatabase,
    private val firebaseAuth: FirebaseAuth
) {

    suspend fun sendPortfolioCoin(portfolioCoinEntity: PortfolioCoinEntity){
        val email = firebaseAuth.currentUser?.email?.replace(".", "") ?: return
        val jsonQuestion = Json.encodeToString(portfolioCoinEntity)
        firebaseDatabase.reference(FIREBASE_PORTFOLIO_COINS).child(email).child(portfolioCoinEntity.id).setValue(jsonQuestion)
    }

    suspend fun portfolioCoinsFlow(): Flow<PortfolioCoinEntity> = channelFlow {
        val email = firebaseAuth.currentUser?.email?.replace(".", "") ?: return@channelFlow
        coroutineScope {
            firebaseDatabase.reference(FIREBASE_PORTFOLIO_COINS).child(email).childEvents().onEach {
                val question = Json.decodeFromString<PortfolioCoinEntity>(it.snapshot.value.toString())
                trySend(question)

            }.launchIn(this)
        }
    }


}