package com.example.user.github.api

import com.example.user.github.api.model.Auth
import com.example.user.github.api.model.RepoSearchResponse
import retrofit2.Call
import retrofit2.http.*

interface AuthApi {

    @FormUrlEncoded
    @POST("login/oauth/access_token")
    @Headers("Accept: application/json")
    fun getAccessToken(@Field("client_id") clientId: String,
                       @Field("client_secret") clientSecret: String,
                       @Field("code") code: String): Call<Auth>

}

interface GithubApi {
    @GET("search/repositories")
    fun searchRepository(@Query("q") query: String): Call<RepoSearchResponse>
}