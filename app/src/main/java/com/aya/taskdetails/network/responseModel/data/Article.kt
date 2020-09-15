package com.aya.taskdetails.network.responseModel.data


import androidx.annotation.Keep

@Keep
data class Article(
    var author: String = "", // Live Code Stream
    var title: String = "", // How to land your first job
    var description: String = "", // So you’re interested in our subbrand
    var url: String = "", // https://thenextweb.com/
    var urlToImage: String = "", // https://img-cdn.tnwcdn.com/image/
    var publishedAt :String = ""  //2020-09-15T08:02:14Z
)