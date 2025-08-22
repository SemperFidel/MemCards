package com.example.database.data.repositoryImpl

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.example.database.data.dto.CardUpdate
import com.example.database.data.entity.Card
import com.example.database.domain.repository.CardRepository
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.InitialResults
import io.realm.kotlin.notifications.UpdatedResults
import io.realm.kotlin.types.RealmUUID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CardRepositoryImpl(private val db: Realm): CardRepository {

    override fun getAllCardsFlow(): Flow<Either<Throwable, List<Card>>> =
        db.query(Card::class)
            .asFlow()
            .map { changes ->
                when (changes) {
                    is InitialResults -> changes.list.right()
                    is UpdatedResults -> changes.list.right()
                    else -> Throwable("Unexpected change type").left()
                }
            }

    override suspend fun getCardById(id: String): Either<Throwable, Card> =
        Either.catch {
            val uuid = RealmUUID.from(id)
            db.query<Card>("_id == $0", uuid).first().find()
                ?: throw NoSuchElementException("Card not found")
        }

    override suspend fun createCard(card: Card): Either<Throwable, Unit> =
        Either.catch {
            db.write {
                copyToRealm(card)
            }
        }.map { }

    override suspend fun updateCard(update: CardUpdate): Either<Throwable, Unit> =
        Either.catch {
        db.write {
            val existing = query<Card>("_id == $0", update.id).first().find()
                ?: throw NoSuchElementException("Card not found")
            findLatest(existing)?.apply {
                update.question?.let { this.question = it }
                update.answer?.let { this.answer = it }
            }
        }
    }.map { }

    override suspend fun deleteCard(card: Card): Either<Throwable, Unit> =
        Either.catch {
            db.write {
                val managedCard = query<Card>("_id == $0", card._id).first().find()
                    ?: throw NoSuchElementException("Card not found")
                delete(managedCard)
            }
        }.map { }
}