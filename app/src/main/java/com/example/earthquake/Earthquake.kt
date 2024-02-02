package com.example.earthquake

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Earthquake(val metadata:Metad,
                      var features:List<Feature>): Parcelable
