package com.example.database.domain.useCase.cardUseCase

import arrow.core.Either
import com.example.database.data.dto.CardUpdate
import com.example.database.domain.repository.CardRepository

class UpdateCardUseCase(
    private val repository: CardRepository
) {
    suspend operator fun invoke(update: CardUpdate): Either<Throwable, Unit>{
        return repository.updateCard(update)
    }
}