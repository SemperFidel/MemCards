package com.example.database.di.useCaseDI

import com.example.database.domain.useCase.deckUseCase.AddDeckUseCase
import com.example.database.domain.useCase.deckUseCase.DeleteDeckUseCase
import com.example.database.domain.useCase.deckUseCase.RemoveCardFromDeckUseCase
import com.example.database.domain.useCase.deckUseCase.UpdateDeckUseCase
import com.example.database.domain.useCase.deckUseCase.AddCardToDeckUseCase
import com.example.database.domain.useCase.deckUseCase.GetAllDecksUseCase
import com.example.database.domain.useCase.deckUseCase.GetDeckByNameUseCase

data class DeckUseCases (
    val getAllDecks: GetAllDecksUseCase,
    val getDeckByName: GetDeckByNameUseCase,
    val createDeck: AddDeckUseCase,
    val updateDeck: UpdateDeckUseCase,
    val deleteDeck: DeleteDeckUseCase,
    val addCardToDeck: AddCardToDeckUseCase,
    val removeCardFromDeck: RemoveCardFromDeckUseCase
)