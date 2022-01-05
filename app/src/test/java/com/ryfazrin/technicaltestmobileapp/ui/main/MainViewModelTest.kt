package com.ryfazrin.technicaltestmobileapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelStore
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@DelicateCoroutinesApi
class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun before() {
        mainViewModel = mock(MainViewModel::class.java)
    }

    @Test
    fun testGetPosts() {
        val job = GlobalScope.launch {
            mainViewModel = mock(MainViewModel::class.java)
        }

        runBlocking {
            job.join()
            mainViewModel.posts.observeForever {
                assertEquals(1, it[0].id)
            }
        }
    }

    fun getId(id: Int): Int {
        return id
    }

    fun testGetListUser() {}

    fun testFindUser() {}
}