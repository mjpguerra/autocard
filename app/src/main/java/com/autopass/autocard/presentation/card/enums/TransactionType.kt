package com.autopass.autocard.presentation.card.enums

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class TransactionType(var log: String) : Parcelable {
    SUCCESS(""),
    BLOCKED(""),
    CANCELED(""),
    INACTIVE(""),
    NO_FUNDS(""),
    OPERATION_ERROR(""),
    LINE_ERROR(""),
    QUOTA_ERROR(""),
    DUE_ERROR(""),
    BALANCE_ERROR(""),
    INVALID(""),
    ERROR("")
}