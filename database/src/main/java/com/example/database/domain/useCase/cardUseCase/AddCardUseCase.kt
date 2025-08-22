package com.example.database.domain.useCase.cardUseCase

import arrow.core.Either
import com.example.database.data.entity.Card
import com.example.database.domain.repository.CardRepository

class AddCardUseCase(
    private val repository: CardRepository
) {
    suspend operator fun invoke(card: Card): Either<Throwable, Unit>{
        return repository.createCard(card)
    }
}