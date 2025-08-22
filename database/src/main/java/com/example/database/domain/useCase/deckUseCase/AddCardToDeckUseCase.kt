package com.example.database.domain.useCase.deckUseCase

import arrow.core.Either
import com.example.database.data.entity.Card
import com.example.database.domain.repository.DeckRepository

class AddCardToDeckUseCase(
    private val repository: DeckRepository
) {
    suspend operator fun invoke(deckId: String, card: Card): Either<Throwable, Unit> {
        return repository.addCardToDeck(deckId, card)
    }}