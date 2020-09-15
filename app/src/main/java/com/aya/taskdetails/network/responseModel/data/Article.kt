package com.aya.taskdetails.network.responseModel.data


import androidx.annotation.Keep

@Keep
data class Article(
    val author: String = "", // Live Code Stream
    val title: String = "", // How to land your first job
    val description: String = "", // So you’re interested in our subbrand
    val url: String = "", // https://thenextweb.com/
    val urlToImage: String = "", // https://img-cdn.tnwcdn.com/image/
    val publishedAt :String = ""  //2020-09-15T08:02:14Z
)