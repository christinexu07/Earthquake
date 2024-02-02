package com.example.earthquake

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Metad(val title: String,
                 val status:Int,
                 val count:Int):Parcelable
