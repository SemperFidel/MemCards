package com.example.mainscreenfeature.di

import com.example.mainscreenfeature.domain.viewmodel.DeckScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val deckViewModelModule = module {
    viewModel {
        DeckScreenViewModel(
            getAllDecks = get(),
            getDeck = get(),
            addDeck = get(),
            updateDeck = get(),
            deleteDeck = get()
        )
    }
}