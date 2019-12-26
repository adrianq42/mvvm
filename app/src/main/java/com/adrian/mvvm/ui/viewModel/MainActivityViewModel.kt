package com.adrian.mvvm.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adrian.mvvm.database.PostEntity
import com.adrian.mvvm.repository.MainActivityRepository

class MainActivityViewModel : ViewModel() {

    private var  mainActivityRepository: MainActivityRepository =
        MainActivityRepository()

    fun getAllPosts(): LiveData<List<PostEntity>> {
        return mainActivityRepository.getAllPosts()
    }

    fun getPostsFromServer(){
        mainActivityRepository.getPostsFromServer()
    }

    fun deleteAllPosts(){
        mainActivityRepository.deleteAllPosts()
    }

    fun addNewPost(post: PostEntity){
        mainActivityRepository.addNewPost(post)
    }

}