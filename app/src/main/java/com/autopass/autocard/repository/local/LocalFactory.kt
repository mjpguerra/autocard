package com.autopass.autocard.repository.local

import com.autopass.autocard.repository.local.general.resources.LocalGeneralResources


interface LocalFactory {
    val general: LocalGeneralResources
}