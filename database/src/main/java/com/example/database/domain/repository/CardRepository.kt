package com.example.database.domain.repository

import arrow.core.Either
import com.example.database.data.dto.CardUpdate
import com.example.database.data.entity.Card
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    fun getAllCardsFlow(): Flow<Either<Throwable, List<Card>>>
    suspend fun getCardById(id: String): Either<Throwable, Card>
    suspend fun createCard(card: Card): Either<Throwable, Unit>
    suspend fun updateCard(update: CardUpdate): Either<Throwable, Unit>
    suspend fun deleteCard(card: Card): Either<Throwable, Unit>
}