package com.example.earthquake

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Feature(var properties:Property,
                   var geometry:Geo): Parcelable


