package com.aya.taskdetails.network

import com.aya.taskdetails.network.responseModel.HomeResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*


interface ApiInterface {


    @GET("v1/articles")
    suspend fun HomeData(
      @Query("source") source :String ,@Query("apikey") apikey:String ): Response<HomeResponse>


}