package com.example.mainscreenfeature.domain.sideEffect

sealed class DeckScreenSideEffect {
    data object ShowMessage: DeckScreenSideEffect()
}