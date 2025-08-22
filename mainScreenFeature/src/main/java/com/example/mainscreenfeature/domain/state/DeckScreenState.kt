package com.example.mainscreenfeature.domain.state

import arrow.core.Option
import arrow.core.none
import com.example.database.data.entity.Deck
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class DeckScreenState(
    val content: List<Deck> = emptyList(),
    val deck: Option<Deck> = none(),
    val isLoading: Boolean = false,
    val error: Option<String> = none()
)
