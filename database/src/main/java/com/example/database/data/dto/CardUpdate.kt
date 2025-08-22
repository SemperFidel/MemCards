package com.example.database.data.dto

import io.realm.kotlin.types.RealmUUID

data class CardUpdate(
    val id: RealmUUID,
    val question: String? = null,
    val answer: String? = null
)
