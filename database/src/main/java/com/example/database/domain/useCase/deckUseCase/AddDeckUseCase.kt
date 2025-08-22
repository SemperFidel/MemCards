package com.example.database.domain.useCase.deckUseCase

import arrow.core.Either
import com.example.database.data.entity.Deck
import com.example.database.domain.repository.DeckRepository

class AddDeckUseCase(
    private val repository: DeckRepository
) {
    suspend operator fun invoke(deck: Deck): Either<Throwable, Unit> {
        return repository.createDeck(deck)
    }
}