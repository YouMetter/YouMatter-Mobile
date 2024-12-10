package com.example.youmatter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.youmatter.LoginActivity
import com.example.youmatter.data.api.Repository
import com.example.youmatter.data.api.RetrofitBuilder
import com.example.youmatter.data.model.ApiResponse
import com.example.youmatter.data.model.ApiResponseError
import com.example.youmatter.data.model.Register.RegisterRequest
import com.example.youmatter.ui.MyViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val repository = Repository(RetrofitBuilder.create())
        viewModel = MyViewModel(repository)

        val namaTextField: TextInputEditText = findViewById(R.id.RegisterInputNama)
        val usernameTextField: TextInputEditText = findViewById(R.id.RegisterInputUsername)
        val emailTextField: TextInputEditText = findViewById(R.id.RegisterInputEmail)
        val passwordTextField: TextInputEditText = findViewById(R.id.RegisterInputPass)
        val submitButton: MaterialButton = findViewById(R.id.ButtonSubmit)
        val loginTextView: TextView = findViewById(R.id.textLogin)


        submitButton.setOnClickListener {
            val request = RegisterRequest(
                usernameTextField.text.toString(),
                namaTextField.text.toString(),
                emailTextField.text.toString(),
                passwordTextField.text.toString()
            )

            viewModel.register(request)

            viewModel.registerResponse.observe(this) { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response) {
                        is ApiResponse.Success -> {
                            Toast.makeText(
                                this@RegisterActivity,
                                "Register Success",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                            startActivity(intent)
                        }

                        is ApiResponse.Failure -> {
                            val registerResponseFail = Gson().fromJson(
                                response.error.toString(),
                                ApiResponseError::class.java
                            )

                            Toast.makeText(
                                this@RegisterActivity,
                                registerResponseFail.error.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

        }
        loginTextView.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}