package com.example.transoapp.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ExampleData {

    @SerializedName("copyright")
    @Expose
    var copyright: String? = null

    @SerializedName("date")
    @Expose
    var date: String? = null

    @SerializedName("explanation")
    @Expose
    var explanation: String? = null

    @SerializedName("hdurl")
    @Expose
    var hdurl: String? = null

    @SerializedName("media_type")
    @Expose
    var mediaType: String? = null

    @SerializedName("service_version")
    @Expose
    var serviceVersion: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

}