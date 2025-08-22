package com.example.database.domain.useCase.cardUseCase

import arrow.core.Either
import com.example.database.data.entity.Card
import com.example.database.domain.repository.CardRepository
import kotlinx.coroutines.flow.Flow

class GetAllCardsUseCase(
    private val repository: CardRepository
) {
    operator fun invoke(): Flow<Either<Throwable, List<Card>>>{
        return repository.getAllCardsFlow()
    }
}