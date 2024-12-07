package com.example.youmatter

import android.R.integer
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.youmatter.data.model.testDepresi.*
import com.example.youmatter.data.model.testDepresi.Request.AnswerOption
import com.example.youmatter.ui.TestAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TestActivity : AppCompatActivity(), TestAdapter.OnButtonClickListener {

    lateinit var TestData: Data
    lateinit var recycleView: RecyclerView
    lateinit var sectionNameTextView: TextView
    lateinit var testNameTextView: TextView
    var currentPage  = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_test)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recycleView = findViewById<RecyclerView>(R.id.questionRecycleView)
        sectionNameTextView = findViewById<TextView>(R.id.sectionName)
        testNameTextView = findViewById<TextView>(R.id.testName)

        sectionNameTextView.text = "Social Settings"
        getTestData()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener{ item ->
        when (item.itemId) {
            R.id.nav_next -> {
                Log.d("Pressed", "NextButton")
                changePage(1)
                true
            }

            R.id.nav_prev -> {
                Log.d("pressed", "PrevButton")
                changePage(0)
                true
            }

            else -> false
        }
        }

    }


    private fun getTestData(){
        val mockResponse = TestDepresiResponse(true, dataDummy,"berhasil" )
        val mockCall = MockCall(mockResponse)

        mockCall.enqueue(object : Callback<TestDepresiResponse>{
            override fun onResponse(
                call: Call<TestDepresiResponse?>,
                response: Response<TestDepresiResponse?>
            ) {

                if (response.body()?.success == true) {
                    TestData = response.body()?.data!!
                    var testAdapter = TestAdapter(TestData.social.questions,this@TestActivity, applicationContext)

                    var layoutManager: LinearLayoutManager = LinearLayoutManager(applicationContext,
                        LinearLayoutManager.VERTICAL, false)

                    recycleView.layoutManager = layoutManager
                    recycleView.adapter = testAdapter

                    currentPage = 1
                }
            }
            override fun onFailure(
                call: Call<TestDepresiResponse?>,
                t: Throwable
            ) {
            }

        })
    }

    private fun changePage(buttonPressed: Int){
        if (currentPage == 1 && buttonPressed == 1){
            var testAdapter = TestAdapter(TestData.workingworld.questions,this@TestActivity, applicationContext)
            recycleView.adapter = testAdapter
            currentPage = 2
            sectionNameTextView.text = "WorkingWorld Settings"
        }
        else if (currentPage == 2 && buttonPressed == 1){
            var testAdapter = TestAdapter(TestData.studying.questions,this@TestActivity, applicationContext)
            recycleView.adapter = testAdapter
            currentPage = 3
            sectionNameTextView.text = "School Settings"

        }else if (currentPage == 3 && buttonPressed == 0){
            var testAdapter = TestAdapter(TestData.workingworld.questions,this@TestActivity, applicationContext)
            recycleView.adapter = testAdapter
            currentPage = 2
            sectionNameTextView.text = "WorkingWorld Settings"
        }else if (currentPage == 2 && buttonPressed == 0){
            var testAdapter = TestAdapter(TestData.social.questions,this@TestActivity, applicationContext)
            recycleView.adapter = testAdapter
            currentPage = 1
            sectionNameTextView.text = "Social Settings"
        }

    }

    override fun onButtonClicked(
        position: Int,
        answerOption: AnswerOption
    ) {
        Log.d("option changed", position.toString())
    }

    val dataDummy = Data("",
        Social("", "Test Social", arrayListOf(
            Questions("1", "Apakah Kamu senang ?", arrayListOf(
                Options("1","Iya"),
                Options("2","tidak"),
                Options("3","Bisa Jadi"),
                Options("4","Tidak Juga"))),
            Questions("1", "Apakah Kamu senang ?", arrayListOf(
                Options("1","Iya"),
                Options("2","tidak"),
                Options("3","Bisa Jadi"),
                Options("4","Tidak Juga"))),
            Questions("1", "Apakah Kamu senang ?", arrayListOf(
                Options("1","Iya"),
                Options("2","tidak"),
                Options("3","Bisa Jadi"),
                Options("4","Tidak Juga"))),
            Questions("1", "Apakah Kamu senang ?", arrayListOf(
                Options("1","Iya"),
                Options("2","tidak"),
                Options("3","Bisa Jadi"),
                Options("4","Tidak Juga"))),
            Questions("1", "Apakah Kamu senang ?", arrayListOf(
                Options("1","Iya"),
                Options("2","tidak"),
                Options("3","Bisa Jadi"),
                Options("4","Tidak Juga"))),)),
        WorkingWorld("", "Test Social", arrayListOf(
            Questions("1", "Apakah Kamu senang ?", arrayListOf(
                Options("1","Iya"),
                Options("2","tidak"),
                Options("3","Bisa Jadi"),
                Options("4","Tidak Juga"))),
            Questions("1", "Apakah Kamu senang ?", arrayListOf(
                Options("1","Iya"),
                Options("2","tidak"),
                Options("3","Bisa Jadi"),
                Options("4","Tidak Juga"))),
            Questions("1", "Apakah Kamu senang ?", arrayListOf(
                Options("1","Iya"),
                Options("2","tidak"),
                Options("3","Bisa Jadi"),
                Options("4","Tidak Juga"))),
            Questions("1", "Apakah Kamu senang ?", arrayListOf(
                Options("1","Iya"),
                Options("2","tidak"),
                Options("3","Bisa Jadi"),
                Options("4","Tidak Juga"))),
            Questions("1", "Apakah Kamu senang ?", arrayListOf(
                Options("1","Iya"),
                Options("2","tidak"),
                Options("3","Bisa Jadi"),
                Options("4","Tidak Juga"))),)),
        Studying("", "Test Social", arrayListOf(
            Questions("1", "Apakah Kamu senang ?", arrayListOf(
                Options("1","Iya"),
                Options("2","tidak"),
                Options("3","Bisa Jadi"),
                Options("4","Tidak Juga"))),
            Questions("1", "Apakah Kamu senang ?", arrayListOf(
                Options("1","Iya"),
                Options("2","tidak"),
                Options("3","Bisa Jadi"),
                Options("4","Tidak Juga"))),
            Questions("1", "Apakah Kamu senang ?", arrayListOf(
                Options("1","Iya"),
                Options("2","tidak"),
                Options("3","Bisa Jadi"),
                Options("4","Tidak Juga"))),
            Questions("1", "Apakah Kamu senang ?", arrayListOf(
                Options("1","Iya"),
                Options("2","tidak"),
                Options("3","Bisa Jadi"),
                Options("4","Tidak Juga"))),
            Questions("1", "Apakah Kamu senang ?", arrayListOf(
                Options("1","Iya"),
                Options("2","tidak"),
                Options("3","Bisa Jadi"),
                Options("4","Tidak Juga"))),))

    )


}