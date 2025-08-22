package com.example.database.domain.useCase.deckUseCase

import arrow.core.Either
import com.example.database.domain.repository.DeckRepository

class RemoveCardFromDeckUseCase(
    private val repository: DeckRepository
) {
    suspend operator fun invoke(deckId: String, cardId: String): Either<Throwable, Unit>{
        return repository.removeCardFromDeck(deckId, cardId)
    }
}