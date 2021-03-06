package com.example.user.github.api.model

import com.google.gson.annotations.SerializedName

data class Auth(
        @field:SerializedName("access_token") val accessToken: String,
        @field:SerializedName("token_type") val tokenType: String)


data class GithubRepo(@field:SerializedName("full_name") val fullName: String, val owner: GithubOwner)

data class RepoSearchResponse(@field:SerializedName("total_count") val totalCount: Int,
                              val items: List<GithubRepo>)

data class GithubOwner(val login: String,
                       @field:SerializedName("avatar_url") val avatarUrl: String)