package com.chabot.vaccination

import java.util.*
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Vaccine(var country: String, var timeline: SortedMap<String, Long>)
    :Parcelable {

    }