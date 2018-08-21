package com.example.user.github.ui

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.example.user.github.R
import com.example.user.github.api.model.GithubRepo
import com.example.user.github.api.provideGithubApi
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.item_repo.view.*
import org.jetbrains.anko.toast


    class RepoViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
    )

    class SearchListAdapter: RecyclerView.Adapter<RepoViewHolder>() {
        var items: List<GithubRepo> = emptyList()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
            return RepoViewHolder(parent)
        }

        override fun getItemCount(): Int {
            return items.count()
        }

        override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
            val item = items[position]
            holder.itemView.repoNameText.text = item.fullName
            holder.itemView.repoOwnerText.text = item.owner.login

            with(holder.itemView) {
                repoNameText.text = item.fullName
                repoOwnerText.text = item.owner.login

                Glide.with(this).load(item.owner.avatarUrl).into(ownerAvatarImage)
            }
        }

    }

//    class SearchListAdapter(val context: Context) : BaseAdapter() {
//        var items: List<GithubRepo> = emptyList()
//
//        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//            val item = items[position]
//
//                val view = LayoutInflater.from(context).inflate(R.layout.item_repo, null)
//                view.repoNameText.text = item.fullName
//                view.repoOwnerText.text = item.owner.login
//            return view
//        }
//
//        override fun getItem(position: Int): Any {
//            return items[position]
//        }
//
//        override fun getItemId(position: Int): Long {
//            return position.toLong()
//            // listView에서 ROW 순서
//            // list에서의 index와 비슷한 역할.
//        }
//
//        override fun getCount(): Int {
//            return items.count()
//        }
//
//    }

class SearchActivity: AppCompatActivity() {
    companion object {
        const val TAG = "SearchActivity"
    }


    lateinit var listAdapter: SearchListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        listAdapter = SearchListAdapter()
        searchListView.adapter = listAdapter
        searchListView.layoutManager = LinearLayoutManager(this)

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