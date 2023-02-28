package com.example.evincepracticaltask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.evincepracticaltask.adapter.UserAdapter
import com.example.evincepracticaltask.data_classes.DataItem
import com.example.evincepracticaltask.database.UserDatabase
import com.example.evincepracticaltask.databinding.ActivityMainBinding
import com.example.evincepracticaltask.listener.OnItemClickListener
import com.example.evincepracticaltask.repo.UserRepo
import com.example.evincepracticaltask.viewmodel.UserViewModel
import com.example.evincepracticaltask.viewmodel.UserViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), OnItemClickListener {
    lateinit var binding : ActivityMainBinding
    lateinit var userViewModel: UserViewModel
    lateinit var userDatabase: UserDatabase
    lateinit var repo: UserRepo
    lateinit var list : ArrayList<DataItem>
    lateinit var userAdapter: UserAdapter
    lateinit var listener : OnItemClickListener

    private fun showData() {
        CoroutineScope(Dispatchers.Main).launch {
            userViewModel.getAllUsers().observe(this@MainActivity, { users ->
                userAdapter = UserAdapter(list,this@MainActivity,listener)
                binding.rvUsers.adapter = userAdapter
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listener = this

        binding.rvUsers.layoutManager = GridLayoutManager(this,3)
        list = ArrayList()
        userAdapter = UserAdapter(list,this,listener)
        binding.rvUsers.adapter = userAdapter

        userDatabase = UserDatabase(this)
        repo = UserRepo(userDatabase)
        userViewModel = ViewModelProvider(this, UserViewModelFactory(repo))[UserViewModel::class.java]

        userViewModel.getAllUsersServer(1).observe(this, Observer {
            list = it.data as ArrayList<DataItem>

            for(i in list){
                userViewModel.insertUser(i)
            }
            showData()
        })
    }

    override fun onItemClick(pos: Int, view: View) {
        userViewModel.deleteUser(list[pos])

        Log.i("delete","delete Called")
    }

    override fun onItemClick2(pos: Int, view: View) {
        userViewModel.updateUser(list[pos])
    }
}