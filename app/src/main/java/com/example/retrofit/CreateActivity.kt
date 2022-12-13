package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit.model.User
import com.example.retrofit.view_model.CreateViewModel

class CreateActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var buttonCreate: Button
    private lateinit var buttonDelete: Button

    private lateinit var viewModel: CreateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        val userId = intent.getStringExtra("user_id")
        initViewModel()
        init()
        createUserObservable()
        if (userId != null) {
            loadUserData(userId)
        }

        buttonCreate.setOnClickListener { createUser(userId) }
        buttonDelete.setOnClickListener { deleteUser(userId) }
    }

    private fun deleteUser(user_id: String?) {
        viewModel.getDeleteUserObservable().observe(this) {
            if (it == null) {
                Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Успешно", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        viewModel.deleteUser(user_id)
    }

    private fun loadUserData(user_id: String) {
        viewModel.getLoadUserObservable().observe(this) {
            if (it != null) {
                nameEditText.setText(it.data?.name)
                emailEditText.setText(it.data?.email)
                buttonCreate.text = "Обновить"
                buttonDelete.visibility = View.VISIBLE
            }
        }
        viewModel.getUserData(user_id)
    }

    private fun init() {
        nameEditText = findViewById(R.id.editTextName)
        emailEditText = findViewById(R.id.editTextEmail)
        buttonCreate = findViewById(R.id.buttonCreate)
        buttonDelete = findViewById(R.id.buttonDelete)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[CreateViewModel::class.java]
    }

    private fun createUser(user_id: String?) {
        val user =
            User("", nameEditText.text.toString(), emailEditText.text.toString(), "Active", "Male")
        if (user_id == null) viewModel.createUser(user)
        else viewModel.updateUser(user_id, user)
    }

    private fun createUserObservable() {
        viewModel.getCreateUserObservable().observe(this) {
            if (it == null) {
                Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Успешно", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}