package com.example.retrofit

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.model.User
import com.example.retrofit.view_model.MainActivityViewModel

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var buttonSearch: Button
    private lateinit var buttonCreate: Button
    private lateinit var searchEditText: EditText
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        initRecyclerView()
        initViewModel()
        searchUser()

        buttonCreate.setOnClickListener { startActivity(Intent(this, CreateActivity::class.java)) }
    }

    private fun init() {
        recyclerView = findViewById(R.id.recyclerView)
        searchEditText = findViewById(R.id.searchEditText)
        buttonSearch = findViewById(R.id.buttonSearch)
        buttonCreate = findViewById(R.id.buttonCreate)
    }

    private fun searchUser() {
        buttonSearch.setOnClickListener {
            if (searchEditText.text.toString().isNotEmpty()) {
                viewModel.searchUser(searchEditText.text.toString())
            } else {
                viewModel.getUsersList()
            }
        }
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
            recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity)
            adapter = recyclerViewAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.getUserListObservable().observe(this) {
            if (it == null) {
                Toast.makeText(this, "NO", Toast.LENGTH_SHORT).show()
            } else {
                recyclerViewAdapter.userList = it.data.toMutableList()
                recyclerViewAdapter.notifyDataSetChanged()
            }
        }
        viewModel.getUsersList()
    }

    override fun onItemClickListener(user: User) {
        val intent = Intent(this, CreateActivity::class.java)
        intent.putExtra("user_id", user.id)
        startActivityForResult(intent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1000) {
            viewModel.getUsersList()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}