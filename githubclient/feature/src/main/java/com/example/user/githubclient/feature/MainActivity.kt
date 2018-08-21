package com.example.user.githubclient.feature

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {

    companion object {
        const val CLIENT_ID = "d3be1425f2b68349c8cf"
        const val CLIENT_SECRET = "8f604dacb00a15456bd8854fe003874d33287c30"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

            // 인터넷 브라우저를 실행하는 Intent
            // => Custom Tabs: android-support-library
            val intent = CustomTabsIntent.Builder().build()
            intent.launchUrl(this, authUri)
        }


    }
}
