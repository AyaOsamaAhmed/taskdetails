package com.aya.taskdetails.network.responseModel


import androidx.annotation.Keep
import com.aya.taskdetails.network.responseModel.data.Article

@Keep
data class HomeResponse(
    val articles: List<Article> = listOf(),
    val status: String = "" ,
    val source :String = "",
    val sortBy  :String =""
)