package com.autopass.autocard.repository.local.general

import android.content.Context
import android.content.SharedPreferences
import com.autopass.autocard.repository.local.general.resources.LocalGeneralResources
import io.reactivex.Completable
import io.reactivex.Single

class LocalGeneralRepository(private val applicationContext: Context) : LocalGeneralResources {

    companion object {
        const val MEDIA_NUMBER = "mediaNumber"
        const val OPERATION_ID = "operationId"
        const val PASSENGER_ID = "passengerId"
        const val DOCUMENT_NUMBER = "documentNumber"
        const val REPOSITORY = "local_general_repository"
    }

    private val sharedPreferences: SharedPreferences by lazy {
        applicationContext.getSharedPreferences(REPOSITORY, Context.MODE_PRIVATE)
    }

    override fun getDocumentNumber(): Single<String> = Single.fromCallable {
        sharedPreferences.getString(DOCUMENT_NUMBER, "")
    }

    override fun saveDocumentNumber(id: String): Completable = Completable.fromCallable {
        sharedPreferences.edit().putString(DOCUMENT_NUMBER, id).apply()
    }


    override fun getMediaNumber(): Single<String> = Single.fromCallable {
        sharedPreferences.getString(MEDIA_NUMBER, "")
    }

    override fun saveMediaNumber(id: String): Completable = Completable.fromCallable {
        sharedPreferences.edit().putString(MEDIA_NUMBER, id).apply()
    }

    override fun cleanMediaNumber(): Completable = Completable.fromCallable {
        sharedPreferences.edit().putInt(MEDIA_NUMBER, 0).apply()
    }

    override fun getOperationId(): Single<Int> = Single.fromCallable {
        sharedPreferences.getInt(OPERATION_ID, 0)
    }

    override fun saveOperationId(id: Int): Completable = Completable.fromCallable {
        sharedPreferences.edit().putInt(OPERATION_ID, id).apply()
    }

    override fun cleanOperationId(): Completable = Completable.fromCallable {
        sharedPreferences.edit().putInt(OPERATION_ID, 0).apply()
    }

    override fun getPassengerId(): Single<Int> = Single.fromCallable {
        sharedPreferences.getInt(PASSENGER_ID, 0)
    }

    override fun savePassengerId(id: Int): Completable = Completable.fromCallable {
        sharedPreferences.edit().putInt(PASSENGER_ID, id).apply()
    }

    override fun cleanPassengerId(): Completable = Completable.fromCallable {
        sharedPreferences.edit().putInt(PASSENGER_ID, 0).apply()
    }

}