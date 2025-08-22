package com.example.database.domain.useCase.deckUseCase

import arrow.core.Either
import com.example.database.data.entity.Deck
import com.example.database.domain.repository.DeckRepository

class GetDeckByNameUseCase(
    private val repository: DeckRepository
) {
    suspend operator fun invoke(name: String): Either<Throwable, Deck>{
        return repository.getDeckByName(name)
    }
}