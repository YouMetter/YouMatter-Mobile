package com.example.youmatter

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MockCall<T>(private val response: T) : Call<T> {
    override fun execute(): Response<T> = Response.success(response)
    override fun enqueue(callback: Callback<T>) {
        callback.onResponse(this, Response.success(response))
    }

    override fun isExecuted(): Boolean {
        TODO("Not yet implemented")
    }

    override fun cancel() {
        TODO("Not yet implemented")
    }

    override fun isCanceled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun clone(): Call<T?> {
        TODO("Not yet implemented")
    }

    override fun request(): Request {
        TODO("Not yet implemented")
    }

    override fun timeout(): Timeout {
        TODO("Not yet implemented")
    }
    // Implement other methods as needed...
}
