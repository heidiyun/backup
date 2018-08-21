package com.example.user.github.ui

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.user.github.R
import com.example.user.github.api.model.GithubRepo
import com.example.user.github.api.provideGithubApi
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.item_repo.view.*
import org.jetbrains.anko.toast

class SearchActivity: AppCompatActivity() {
    companion object {
        const val TAG = "SearchActivity"
    }

    class SearchListAdapter(val context: Context) : BaseAdapter() {
        var items: List<GithubRepo> = emptyList()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val item = items[position]

                val view = LayoutInflater.from(context).inflate(R.layout.item_repo, null)
                view.repoNameText.text = item.fullName
                view.repoOwnerText.text = item.owner.login
            return view
        }

        override fun getItem(position: Int): Any {
            return items[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
            // listView에서 ROW 순서
            // list에서의 index와 비슷한 역할.
        }

        override fun getCount(): Int {
            return items.count()
        }

    }

    lateinit var listAdapter: SearchListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        listAdapter = SearchListAdapter(this)
        searchListView.adapter = listAdapter

        val githubApi = provideGithubApi(this)
        val call = githubApi.searchRepository("TIL")
        call.enqueue({
            response ->
          val statusCode = response.code()
            if (statusCode == 200) {
                val result = response.body()
                result?. let {
                    listAdapter.items = it.items
                    listAdapter.notifyDataSetChanged()
                }
            } else{
                toast("error - $statusCode")
            }
        }, {
                t ->
            toast(t.localizedMessage)
        })
    }
}