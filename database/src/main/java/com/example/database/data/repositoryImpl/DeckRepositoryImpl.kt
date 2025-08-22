package com.example.database.data.repositoryImpl

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.example.database.data.entity.Card
import com.example.database.data.entity.Deck
import com.example.database.domain.repository.DeckRepository
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.InitialResults
import io.realm.kotlin.notifications.UpdatedResults
import io.realm.kotlin.types.RealmUUID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DeckRepositoryImpl(private val db: Realm): DeckRepository {

    override fun getAllDecksFlow(): Flow<Either<Throwable, List<Deck>>> =
        db.query(Deck::class)
            .asFlow()
            .map { changes ->
                when (changes) {
                    is InitialResults -> changes.list.right()
                    is UpdatedResults -> changes.list.right()
                    else -> Throwable("Unexpected change type").left()
                }
            }

    override suspend fun getDeckByName(name: String): Either<Throwable, Deck> =
        Either.catch {
            db.query<Deck>("name == $0", name)
                .first()
                .find()
                ?: throw NoSuchElementException("Deck with name '$name' not found")
        }

    override suspend fun createDeck(deck: Deck): Either<Throwable, Unit> =
        Either.catch {
            db.write {
                copyToRealm(deck)
            }
        }.map { }


    override suspend fun updateDeck(deck: Deck): Either<Throwable, Unit> =
        Either.catch {
            db.write {
                val existing = query<Deck>("_id == $0", deck._id).first().find()
                    ?: throw NoSuchElementException("Deck not found")
                findLatest(existing)?.apply {
                    name = deck.name
                }
            }
        }.map { }

    override suspend fun addCardToDeck(deckId: String, card: Card): Either<Throwable, Unit> =
        Either.catch {
            db.write {
                val deck = query<Deck>("_id == $0", RealmUUID.from(deckId)).first().find()
                    ?: throw NoSuchElementException("Deck not found")

                val managedCard = copyToRealm(card)
                deck.cards.add(managedCard)
            }
        }.map { }

    override suspend fun removeCardFromDeck(deckId: String, cardId: String): Either<Throwable, Unit> =
        Either.catch {
            db.write {
                val deck = query<Deck>("_id == $0", RealmUUID.from(deckId)).first().find()
                    ?: throw NoSuchElementException("Deck not found")

                val cardToRemove = deck.cards.firstOrNull { it._id.toString() == cardId }
                    ?: throw NoSuchElementException("Card not found in deck")

                deck.cards.remove(cardToRemove)

                delete(cardToRemove)
            }
        }.map { }


    override suspend fun deleteDeck(deck: Deck): Either<Throwable, Unit> =
        Either.catch {
            db.write {
                val managedDeck = query<Deck>("_id == $0", deck._id).first().find()
                    ?: throw NoSuchElementException("Deck not found")

                managedDeck.cards.forEach {
                    delete(it)
                }
                delete(managedDeck)
            }
        }.map { }
}