package com.example.youmatter.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.youmatter.Utils.Event
import com.example.youmatter.data.api.Repository
import com.example.youmatter.data.model.ApiResponse
import com.example.youmatter.data.model.Login.LoginRequest
import com.example.youmatter.data.model.Login.LoginResponseSuccess
import com.example.youmatter.data.model.Register.RegisterRequest
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

class MyViewModel(private val repository: Repository): ViewModel() {
    private val _loginResponse = MutableLiveData<Event<ApiResponse<LoginResponseSuccess>>>()
    val loginResponse: LiveData<Event<ApiResponse<LoginResponseSuccess>>> get() = _loginResponse

    private val _registerResponse = MutableLiveData<Event<ApiResponse<JsonObject>>>()
    val registerResponse: LiveData<Event<ApiResponse<JsonObject>>> get() = _registerResponse

    fun login(loginData: LoginRequest){
        viewModelScope.launch{
            val result = repository.loginRequest(loginData)
            _loginResponse.postValue(Event(result))
        }
    }

    fun register(registerData: RegisterRequest){
        viewModelScope.launch{
            val result = repository.registerRequest(registerData)
        _registerResponse.postValue(Event(result))}
    }


}