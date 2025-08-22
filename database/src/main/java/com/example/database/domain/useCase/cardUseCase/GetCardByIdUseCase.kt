package com.example.database.domain.useCase.cardUseCase

import arrow.core.Either
import com.example.database.data.entity.Card
import com.example.database.domain.repository.CardRepository

class GetCardByIdUseCase(
    private val repository: CardRepository
) {
    suspend operator fun invoke(id: String): Either<Throwable, Card> {
        return repository.getCardById(id)
    }
}