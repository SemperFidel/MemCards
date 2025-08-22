package com.example.mainscreenfeature.domain.viewmodel

import androidx.lifecycle.ViewModel
import arrow.core.None
import arrow.core.Some
import arrow.core.none
import com.example.database.data.entity.Deck
import com.example.database.domain.useCase.deckUseCase.AddDeckUseCase
import com.example.database.domain.useCase.deckUseCase.DeleteDeckUseCase
import com.example.database.domain.useCase.deckUseCase.GetAllDecksUseCase
import com.example.database.domain.useCase.deckUseCase.GetDeckByNameUseCase
import com.example.database.domain.useCase.deckUseCase.UpdateDeckUseCase
import com.example.mainscreenfeature.domain.intent.DeckScreenIntent
import com.example.mainscreenfeature.domain.sideEffect.DeckScreenSideEffect
import com.example.mainscreenfeature.domain.state.DeckScreenState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class DeckScreenViewModel(
    private val getAllDecks: GetAllDecksUseCase,
    private val addDeck: AddDeckUseCase,
    private val getDeck: GetDeckByNameUseCase,
    private val updateDeck: UpdateDeckUseCase,
    private val deleteDeck: DeleteDeckUseCase
): ContainerHost<DeckScreenState, DeckScreenSideEffect>, ViewModel(){

    override val container = container<DeckScreenState, DeckScreenSideEffect>(
        initialState = DeckScreenState()
    )

    fun onIntent(intent: DeckScreenIntent){
        when(intent){
            is DeckScreenIntent.GetDeckByNameIntent -> loadDeckByName(intent.name)
            is DeckScreenIntent.AddDeckIntent -> addNewDeck(intent.deck)
            is DeckScreenIntent.UpdateDeckIntent -> updateChosenDeck(intent.deck)
            is DeckScreenIntent.DeleteDeckIntent -> deleteChosenDeck(intent.deck)
        }
    }

    private fun loadDecks() = intent {
        reduce { state.copy(isLoading = true) }

        getAllDecks()
            .collect { either ->
                either.fold(
                    ifLeft = { error ->
                        reduce { state.copy(isLoading = false, error = Some(error.message ?: "Unknown")) }
                    },
                    ifRight = { decks ->
                        reduce { state.copy(isLoading = false, content = decks, error = None) }
                    }
                )
            }
    }

    private fun loadDeckByName(name: String) = intent {
        reduce { state.copy(isLoading = true, error = none()) }

        val result = getDeck(name)

        result.fold(
            ifLeft = { throwable ->
                reduce {
                    state.copy(
                        isLoading = false,
                        error = Some(throwable.message ?: "Failed to load deck")
                    )
                }
            },
            ifRight = { deck ->
                reduce {
                    state.copy(
                        isLoading = false,
                        deck = Some(deck),
                        error = none()
                    )
                }
            }
        )
    }

    private fun addNewDeck(deck: Deck) = intent {
        reduce { state.copy(isLoading = true, error = none()) }

        val result = addDeck(deck)

        result.fold(
            ifLeft = { throwable ->
                reduce {
                    state.copy(
                        isLoading = false,
                        error = Some(throwable.message ?: "Failed to add deck")
                    )
                }
            },
            ifRight = {
                reduce { state.copy(isLoading = false) }
                loadDecks()
            }
        )
    }

    private fun updateChosenDeck(deck: Deck) = intent {
        reduce { state.copy(isLoading = true, error = none()) }

        val result = updateDeck(deck)

        result.fold(
            ifLeft = { throwable ->
                reduce {
                    state.copy(
                        isLoading = false,
                        error = Some(throwable.message ?: "Failed to update deck")
                    )
                }
            },
            ifRight = {
                reduce { state.copy(isLoading = false) }
                loadDecks()
            }
        )
    }

    private fun deleteChosenDeck(deck: Deck) = intent {
        reduce { state.copy(isLoading = true, error = none()) }

        val result =  deleteDeck(deck)

        result.fold(
            ifLeft = { throwable ->
                reduce {
                    state.copy(
                        isLoading = false,
                        error = Some(throwable.message ?: "Failed to delete deck")
                    )
                }
            },
            ifRight = {
                reduce { state.copy(isLoading = false) }
                loadDecks()
            }
        )
    }
}