package com.chabot.vaccination

import retrofit2.Call
import retrofit2.http.GET

interface WorldwideInfo {
    @GET("all")
    fun getWorldwideStats():Call<Worldwide>
}