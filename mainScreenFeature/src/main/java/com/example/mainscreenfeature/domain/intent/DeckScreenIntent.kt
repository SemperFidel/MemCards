package com.example.mainscreenfeature.domain.intent

import com.example.database.data.entity.Deck

sealed class DeckScreenIntent {
    data class AddDeckIntent(val deck: Deck): DeckScreenIntent()
    data class GetDeckByNameIntent(val name: String): DeckScreenIntent()
    data class UpdateDeckIntent(val deck: Deck): DeckScreenIntent()
    data class DeleteDeckIntent(val deck: Deck): DeckScreenIntent()
}