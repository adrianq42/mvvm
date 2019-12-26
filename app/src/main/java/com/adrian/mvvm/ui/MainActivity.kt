package com.adrian.mvvm.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrian.mvvm.database.PostEntity
import com.adrian.mvvm.ui.viewModel.MainActivityViewModel
import androidx.recyclerview.widget.RecyclerView
import com.adrian.mvvm.R
import com.adrian.mvvm.adapters.Posts_RecyclerView_adapter
import com.adrian.mvvm.adapters.Posts_RecyclerView_listener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private var postList = arrayListOf<PostEntity>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Posts_RecyclerView_adapter
    private val layoutManager by lazy { LinearLayoutManager(this) }
    private var lastId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = postsRecyclerView

        btnAdd.setOnClickListener { addItem() }
        btnClean.setOnClickListener { cleanList() }
        btnGet.setOnClickListener { getAllPost() }

        initList()

        setupViewModel()

    }

    private fun setupViewModel() {

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        viewModel.getPostsFromServer()

        viewModel.getAllPosts().observe(this, Observer {
            it?.let { posts ->
                if(posts.isNotEmpty()){
                    lastId = posts[posts.size -1].id
                }
                setRecyclerView(posts)
            }
        })
    }

    private fun addItem(){
        Log.d("Action:", "Adding item")
        val post = PostEntity(id = lastId + 1,userId = 1,title = "New Post Title",completed = true)
        viewModel.addNewPost(post)
    }

    private fun cleanList(){
        Log.d("Action:", "Clean list")
        viewModel.deleteAllPosts()
    }

    private fun getAllPost(){
        Log.d("Action:", "Getting items")
        viewModel.getPostsFromServer()
    }


    private fun initList(){
        postList = arrayListOf()
    }

    private fun setRecyclerView(list: List<PostEntity>){

        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutManager = layoutManager

        val aList: ArrayList<PostEntity> = list as ArrayList<PostEntity>

        aList.reverse()

        adapter = (Posts_RecyclerView_adapter(aList, object: Posts_RecyclerView_listener {

            override fun onClick(item: PostEntity, position: Int) {
                //TODO: Implementar click del item
            }

        }))

        recyclerView.adapter = adapter
    }
}
