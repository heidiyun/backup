package com.example.user.github.api

import android.content.Context
import android.preference.PreferenceManager

const val KEY_AUTH_TOKEN = "user.example.github.auth_token"

//App의 설정은 PreferenceManager을 통해서 관리한다.

fun updateToken(context: Context, token: String) {
    PreferenceManager.getDefaultSharedPreferences(context).edit()
            .putString(KEY_AUTH_TOKEN, token) // KEY_AUTH_TOKEN 에 파라미터로 전달받은 token 값을 넣어준다.
            .apply()
}
//Context는 특정 안드로이드 시스템에 접근할 수 있도록 해준다.

fun getToken(context: Context): String? {
    return PreferenceManager.getDefaultSharedPreferences(context)
            .getString(KEY_AUTH_TOKEN, null) // 현재 설정에 저장되어 있는 token 값을 반환한다.
    // return 타입이 String nullable, 만약 값이 없을 경우에는 기본값을 null 로 지정
}
