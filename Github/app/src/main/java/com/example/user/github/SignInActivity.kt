package com.example.user.github

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ListView
import com.example.user.github.api.authApi
import com.example.user.github.api.model.Auth
import com.example.user.github.api.provideGithubApi
import com.example.user.github.api.updateToken
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_sign_in.*
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.jetbrains.anko.toast
import java.io.IOException

// retrofit : 요청하는 코드에서 반복되는 보일러플레이트를 없애주는 역할.
class SignInActivity : AppCompatActivity() {


    companion object {
        val TAG = SignInActivity::class.java.simpleName
        const val CLIENT_ID = "d3be1425f2b68349c8cf"
        const val CLIENT_SECRET = "8f604dacb00a15456bd8854fe003874d33287c30"
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        signingButton.setOnClickListener {
            // https://github.com/login/oauth/authorize?client_id=XXX
            val authUri = Uri.Builder().scheme("https")
                    .authority("github.com")
                    .appendPath("login")
                    .appendPath("oauth")
                    .appendPath("authorize")
                    .appendQueryParameter("client_id", CLIENT_ID)
                    .build()

            toast(authUri.toString())

            // github 로그인 창의 브라우저를 실행해주는 Intent
            val intent = CustomTabsIntent.Builder().build()
            intent.launchUrl(this, authUri)

        }
    }

    // 의도
    // : 어떤 일을 할 것인지.
    //   어떤 동작을 수행하게 할 것인지.
    // 여기서는 나의 앱을 다시 띄운다.

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        toast("onNewIntent")
        check(intent != null)
        check(intent?.data != null)

        val uri = intent?.data
        val code = uri?.getQueryParameter("code") ?: throw IllegalStateException("no code!")

        getAccessToken(code)
    }


    private fun post(url: String, clientId: String, clientSecret: String, code: String) {
        val map = mapOf(
                "client_id" to clientId,
                "client_secret" to clientSecret,
                "code" to code
        )

        val gson = GsonBuilder().create()
        val json = gson.toJson(map)

        val requestBody = RequestBody
                .create(MediaType.parse("application/json; charset=utf-8"), json)
        val request = Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .post(requestBody).build()

        val call = httpClient.newCall(request)

        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    toast("Failed")
                }
            }

            override fun onResponse(call: Call, response: Response) {
                runOnUiThread {
                    toast("Succeed")

                    val statusCode = response.code()
                    when (statusCode) {
                        in 200.until(300) -> {
                            toast("Status OK")
                            //val json = response.body()!!.string()

                            response.body()?.let {
                                toast(it.string())
                            }
                            //toast(json)
                        }

                        in 400.until(500) -> toast("Client Error")
                        in 500.until(600) -> toast("Server Error")
                    }
                }
            }
        })
    }
//     post의 역할을 AuthApi에 넘겨줬으니 필요가 없다.


    private fun getAccessToken(code: String) {
        Log.i(TAG, "getAccessToken: $code")

        post("https://github.com/login/oauth/access_token",
                CLIENT_ID, CLIENT_SECRET, code)

//        val call = authApi.getAccessToken(CLIENT_ID, CLIENT_SECRET, code)
//        call.enqueue(object: Callback<Auth> {
//            override fun onFailure(call: Call<Auth>, t: Throwable) {
//                   toast(t.message.toString())
//            }
//
//            override fun onResponse(call: Call<Auth>, response: Response<Auth>) {
//                response.body()?.let {
//                    toast(it.toString())
//                }
//            }
//        })

//        val call = authApi.getAccessToken(CLIENT_ID, CLIENT_SECRET, code)
//        call.enqueue({
//            it.body()?.let {
//                toast(it.toString())
//                updateToken(this, it.accessToken)
//
//
//                val githubApiCall = provideGithubApi(this).searchRepository("TIL")
//                githubApiCall.enqueue({
//                    it.body()?.let {
//                        Log.i(TAG, "total_count: ${it.totalCount}")
//                        Log.i(TAG, "items: ${it.items}")
//
//                    }
//                }, {
//
//                })
//            }
//        }, {
//            toast(it.message.toString())
//        })
//    }
    }
}

//fun <T> Call<T>.enqueue(success: (response: Response<T>) -> Unit, failure: (t: Throwable) -> Unit) {
//    enqueue(object:  Callback<T> {
//        override fun onFailure(call: Call<T>, t: Throwable) = failure(t)
//        override fun onResponse(call: Call<T>, response: Response<T>) = success(response)
//    })
//}
