package com.example.youmatter

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.youmatter.RegisterActivity
import com.example.youmatter.data.api.Repository
import com.example.youmatter.data.api.RetrofitBuilder
import com.example.youmatter.data.model.ApiResponse
import com.example.youmatter.data.model.Error
import com.example.youmatter.data.model.Login.LoginRequest
import com.example.youmatter.data.model.ApiResponseError
import com.example.youmatter.ui.MyViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val repository = Repository(RetrofitBuilder.create())
        viewModel = MyViewModel(repository)

        val usernameTextField: TextInputEditText = findViewById(R.id.loginInputUsername)
        val passwordTextField: TextInputEditText = findViewById(R.id.loginInputPass)
        val submitButton: MaterialButton = findViewById(R.id.LoginButton)
        val registerTextView: TextView = findViewById(R.id.RegisterText)

        submitButton.setOnClickListener {
            val request = LoginRequest(
                usernameTextField.text.toString(),
                passwordTextField.text.toString()
            )

            viewModel.login(request)

            viewModel.loginResponse.observe(this) { event ->
                event.getContentIfNotHandled()?.let { response ->
                    when (response) {
                        is ApiResponse.Success -> {
                            val loginResponse = response.data
                            val sharedPreferences =
                                getSharedPreferences("MySharedPref", MODE_PRIVATE)
                            val editor = sharedPreferences.edit()
                            editor.putString("TOKEN", loginResponse.data.token)
                            editor.apply()
//                        Log.d("Token", loginResponse.data.token.toString())

                            Toast.makeText(
                                this@LoginActivity,
                                "Login Success",
                                Toast.LENGTH_SHORT
                            ).show()

                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                        }

                        is ApiResponse.Failure -> {
                            val loginResponseFail = Gson().fromJson(
                                response.error.toString(),
                                ApiResponseError::class.java
                            )

                            Toast.makeText(
                                this@LoginActivity,
                                loginResponseFail.error.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                }
            }


        }
        registerTextView.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

}