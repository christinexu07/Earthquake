package com.example.earthquake

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Geo(var coordinates:List<Double>): Parcelable
