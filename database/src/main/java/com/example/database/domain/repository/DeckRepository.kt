package com.example.database.domain.repository

import arrow.core.Either
import com.example.database.data.entity.Card
import com.example.database.data.entity.Deck
import kotlinx.coroutines.flow.Flow

interface DeckRepository {
    fun getAllDecksFlow(): Flow<Either<Throwable, List<Deck>>>
    suspend fun getDeckByName(name: String): Either<Throwable, Deck>
    suspend fun createDeck(deck: Deck): Either<Throwable, Unit>
    suspend fun updateDeck(deck: Deck): Either<Throwable, Unit>
    suspend fun addCardToDeck(deckId: String, card: Card): Either<Throwable, Unit>
    suspend fun removeCardFromDeck(deckId: String, cardId: String): Either<Throwable, Unit>
    suspend fun deleteDeck(deck: Deck): Either<Throwable, Unit>

}