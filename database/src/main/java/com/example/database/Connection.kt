package com.example.database

import com.example.database.data.entity.Card
import com.example.database.data.entity.Deck
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class Connection {
    fun connectDatabase(): Realm {
        val config = RealmConfiguration.create(schema = setOf(Card::class, Deck::class))
        val db = Realm.open(config)
        return db
    }
}