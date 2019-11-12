package com.autopass.autocard.repository

import android.content.Context
import com.autopass.autocard.repository.local.LocalFactory
import com.autopass.autocard.repository.local.LocalRepository
import com.autopass.autocard.repository.remote.RemoteFactory
import com.autopass.autocard.repository.remote.RemoteRepository

class Repository(applicationContext: Context) : RepositoryFactory {
    override val local: LocalFactory = LocalRepository(applicationContext)
    override val remote: RemoteFactory = RemoteRepository()
}