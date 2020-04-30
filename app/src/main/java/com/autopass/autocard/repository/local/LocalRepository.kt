package com.autopass.autocard.repository.local

import android.content.Context
import com.autopass.autocard.repository.local.general.LocalGeneralRepository
import com.autopass.autocard.repository.local.general.resources.LocalGeneralResources
import org.koin.core.KoinComponent

class LocalRepository(applicationContext: Context) : LocalFactory, KoinComponent {

    override val general: LocalGeneralResources = LocalGeneralRepository(applicationContext)

}