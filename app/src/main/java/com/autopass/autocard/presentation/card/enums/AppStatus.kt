package com.autopass.autocard.presentation.card.enums

enum class AppStatus(val id: Int) {
    ACTIVE(1),
    INACTIVE(2),
    EXPIRED(3),
    BLOCKED(4),
    CANCELED(5),
    SUSPENDED(6)
}