package com.nevereatalone.data.api

import com.nevereatalone.data.api.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * App API Endpoint
 */
interface ApiService {

    @GET
    fun getUserProfile(@Query("userId") userId:String): Single<User>
}