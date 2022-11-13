package com.example.transoapp.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AgeData {

    @SerializedName("age")
    @Expose
    var age = 0

    @SerializedName("count")
    @Expose
    var count = 0

    @SerializedName("name")
    @Expose
    var name: String? = null


}