package com.autopass.autocard.repository

import com.autopass.autocard.repository.local.LocalFactory
import com.autopass.autocard.repository.remote.RemoteFactory


interface RepositoryFactory {
    val local: LocalFactory
    val remote: RemoteFactory
}