package com.aya.taskdetails.network.repository

import android.content.Context
import com.aya.taskdetails.network.ApiException
import com.aya.taskdetails.network.ApiInterface
import com.aya.taskdetails.network.SafeAmiRequestLoadingDialogue

class GeneralDataRepository(
    private val api: ApiInterface,
    private val context: Context
) : SafeAmiRequestLoadingDialogue(context) {


    @Throws(ApiException::class)
    suspend fun getHomeData() =
        apiRequest { api.HomeData("the-next-web","533af958594143758318137469b41ba9") }

    @Throws(ApiException::class)
    suspend fun getHomeData_2() =
        apiRequest { api.HomeData("associated-press","533af958594143758318137469b41ba9") }


}