package com.example.database.domain.useCase.deckUseCase

import arrow.core.Either
import com.example.database.data.entity.Deck
import com.example.database.domain.repository.DeckRepository
import kotlinx.coroutines.flow.Flow

class GetAllDecksUseCase(
    private val repository: DeckRepository
) {
    operator fun invoke(): Flow<Either<Throwable, List<Deck>>>{
        return repository.getAllDecksFlow()
    }
}