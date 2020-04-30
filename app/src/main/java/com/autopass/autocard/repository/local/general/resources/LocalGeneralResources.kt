package com.autopass.autocard.repository.local.general.resources

import io.reactivex.Completable
import io.reactivex.Single

interface LocalGeneralResources {
    fun getDocumentNumber(): Single<String>
    fun saveDocumentNumber(id: String): Completable
    fun getMediaNumber(): Single<String>
    fun saveMediaNumber(id: String): Completable
    fun cleanMediaNumber(): Completable
    fun getOperationId(): Single<Int>
    fun saveOperationId(id: Int): Completable
    fun cleanOperationId(): Completable
    fun getPassengerId(): Single<Int>
    fun savePassengerId(id: Int): Completable
    fun cleanPassengerId(): Completable
}