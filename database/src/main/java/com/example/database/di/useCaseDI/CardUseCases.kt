package com.example.database.di.useCaseDI

import com.example.database.domain.useCase.cardUseCase.AddCardUseCase
import com.example.database.domain.useCase.cardUseCase.DeleteCardUseCase
import com.example.database.domain.useCase.cardUseCase.GetAllCardsUseCase
import com.example.database.domain.useCase.cardUseCase.GetCardByIdUseCase
import com.example.database.domain.useCase.cardUseCase.UpdateCardUseCase

data class CardUseCases(
    val getAllCards: GetAllCardsUseCase,
    val getCardById: GetCardByIdUseCase,
    val updateCard: UpdateCardUseCase,
    val createCard: AddCardUseCase,
    val deleteCard: DeleteCardUseCase
)
