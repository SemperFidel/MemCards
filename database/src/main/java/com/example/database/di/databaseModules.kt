package com.example.database.di

import com.example.database.Connection
import com.example.database.data.repositoryImpl.CardRepositoryImpl
import com.example.database.data.repositoryImpl.DeckRepositoryImpl
import com.example.database.di.useCaseDI.CardUseCases
import com.example.database.domain.repository.CardRepository
import com.example.database.domain.repository.DeckRepository
import com.example.database.domain.useCase.deckUseCase.GetAllDecksUseCase
import com.example.database.domain.useCase.deckUseCase.GetDeckByNameUseCase
import com.example.database.domain.useCase.deckUseCase.AddDeckUseCase
import com.example.database.domain.useCase.deckUseCase.UpdateDeckUseCase
import com.example.database.domain.useCase.deckUseCase.DeleteDeckUseCase
import com.example.database.domain.useCase.deckUseCase.AddCardToDeckUseCase
import com.example.database.domain.useCase.deckUseCase.RemoveCardFromDeckUseCase
import com.example.database.di.useCaseDI.DeckUseCases
import com.example.database.domain.useCase.cardUseCase.AddCardUseCase
import com.example.database.domain.useCase.cardUseCase.DeleteCardUseCase
import com.example.database.domain.useCase.cardUseCase.GetAllCardsUseCase
import com.example.database.domain.useCase.cardUseCase.GetCardByIdUseCase
import com.example.database.domain.useCase.cardUseCase.UpdateCardUseCase
import io.realm.kotlin.Realm
import org.koin.dsl.module

val databaseModule = module {
    single<Realm> { Connection().connectDatabase() }
}

val cardRepositoryModule = module {
    single<CardRepository> { CardRepositoryImpl(get()) }
}

val deckRepositoryModule = module {
    single<DeckRepository> { DeckRepositoryImpl(get()) }
}

val deckUseCaseModule = module {
    factory { GetAllDecksUseCase(get()) }
    factory { GetDeckByNameUseCase(get()) }
    factory { AddDeckUseCase(get()) }
    factory { UpdateDeckUseCase(get()) }
    factory { DeleteDeckUseCase(get()) }
    factory { AddCardToDeckUseCase(get()) }
    factory { RemoveCardFromDeckUseCase(get()) }

    factory {
        DeckUseCases(
            getAllDecks = get(),
            getDeckByName = get(),
            createDeck = get(),
            updateDeck = get(),
            deleteDeck = get(),
            addCardToDeck = get(),
            removeCardFromDeck = get()
        )
    }
}

val cardUseCaseModule = module {
    factory { GetAllCardsUseCase(get()) }
    factory { GetCardByIdUseCase(get()) }
    factory { AddCardUseCase(get()) }
    factory { UpdateCardUseCase(get()) }
    factory { DeleteCardUseCase(get()) }

    factory {
        CardUseCases(
            getAllCards = get(),
            getCardById = get(),
            updateCard = get(),
            createCard = get(),
            deleteCard = get()
        )
    }
}