package com.example.user.github.api

import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
    // debug시 보여지는 정보의 레벨 단계
}

val httpClient: OkHttpClient = OkHttpClient.Builder().apply {
    addInterceptor(loggingInterceptor)
}.build()

val authApi: AuthApi = Retrofit.Builder().apply {
    baseUrl("https://github.com/")
    client(httpClient)
    addConverterFactory(GsonConverterFactory.create())
    // 객체로 정보를 맵핑한다.
}.build().create(AuthApi::class.java)

class AuthInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
       val original = chain.request()

        val request = original.newBuilder().apply {
            getToken(context)?.let {
                token -> addHeader("Authorization", "bearer $token")
            }
        }.build()

        return chain.proceed(request)
    }

}
// 요청을 할 때 마다 중간다리 역할을 한다. 중간 layer를 가지고 수행이 된다.

fun authHttpClient(context: Context) = OkHttpClient.Builder().apply{
    addInterceptor(AuthInterceptor(context))
}.build()

fun provideGithubApi(context: Context) = Retrofit.Builder().apply {
    baseUrl("https://api.github.com/")
    client(authHttpClient(context))
    addConverterFactory(GsonConverterFactory.create())
}.build().create(GithubApi::class.java)