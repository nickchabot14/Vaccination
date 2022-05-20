package com.chabot.vaccination

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Worldwide(
    val cases: Long,
    val active : Long,
    val todaycases : Long,
    )

    : Parcelable {

    }
