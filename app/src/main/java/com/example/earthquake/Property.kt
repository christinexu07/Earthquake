package com.example.earthquake

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Property(var mag:Double,
                    var title:String,
                    var place:String,
                    var time:Long,
                    var url:String):Parcelable
