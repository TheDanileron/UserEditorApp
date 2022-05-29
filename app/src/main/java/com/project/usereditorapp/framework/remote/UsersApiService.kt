package com.project.usereditorapp.framework.remote

import com.google.gson.GsonBuilder
import com.project.core.domain.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*

interface UsersApiService {

    @GET("users/")
    @Headers(
        "Authorization: Bearer YOUR_API_KEY"
    )
    fun getUsers() : Call<List<User>>

    @DELETE("users/{user_id}")
    @Headers(
        "Authorization: Bearer YOUR_API_KEY"
    )
    fun delete(@Path(value = "user_id", encoded = true) id: String) : Call<Unit>

    @PUT("users/{user_id}")
    @Headers(
        "Authorization: Bearer YOUR_API_KEY"
    )
    fun update(@Path(value = "user_id", encoded = true) id: String, @Body user: User) : Call<User>

    companion object {
        const val BASE_URL = "https://gorest.co.in/public/v2/"

        private var retrofitService: UsersApiService? = null

        fun getInstance() : UsersApiService {
            if (retrofitService == null) {
                val gson = GsonBuilder()
                    .setLenient()
                    .create()
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                retrofitService = retrofit.create(UsersApiService::class.java)
            }
            return retrofitService!!
        }
    }
}