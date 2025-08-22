package com.example.database.data.entity

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.RealmUUID
import io.realm.kotlin.types.annotations.PrimaryKey

class Card: RealmObject {
    @PrimaryKey
    val _id: RealmUUID = RealmUUID.random()
    var question: String? = ""
    var answer: String? = ""
}