package com.example.youmatter

import android.R.integer
import android.content.res.ColorStateList
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
import kotlin.collections.arrayListOf
import android.graphics.Color
import androidx.core.content.ContextCompat
import android.os.Handler
import android.os.Looper

class TestActivity : AppCompatActivity(), TestAdapter.OnButtonClickListener {

    lateinit var TestData: Data
    lateinit var recycleView: RecyclerView
    lateinit var sectionNameTextView: TextView
    lateinit var testNameTextView: TextView
    lateinit var bottomNavigationView: BottomNavigationView
    var socialAnswerList: ArrayList<Any> = arrayListOf()
    var workingWorldAnswerList: ArrayList<Any>  = arrayListOf()
    var studyingAnswerList: ArrayList<Any> = arrayListOf()

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
        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        val colorStateList = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_pressed), // Pressed
                intArrayOf(-android.R.attr.state_pressed) // Default
            ),
            intArrayOf(
                ContextCompat.getColor(this, R.color.button_outline_color_for_option), // Color when pressed
                ContextCompat.getColor(this, R.color.card_border_color) // Default color
            )
        )

        // Apply the ColorStateList to the item text and icon
        bottomNavigationView.itemIconTintList = colorStateList
        bottomNavigationView.itemTextColor = colorStateList


        bottomNavigationView.setOnItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_next -> {
                Log.d("Pressed", "NextButton")
                changePage(1)
                false
            }

            R.id.nav_prev -> {
                Log.d("pressed", "PrevButton")
                changePage(0)
                false
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

                    TestData.social.questions?.let {
                        while (socialAnswerList.size < it.size){
                            socialAnswerList.add("")
                        }
                    }
                    TestData.workingworld.questions?.let {
                        while (workingWorldAnswerList.size < it.size){
                            workingWorldAnswerList.add("")
                        }
                    }
                    TestData.studying.questions?.let {
                        while (studyingAnswerList.size < it.size){
                            studyingAnswerList.add("")
                        }
                    }

                    currentPage = 1

                    var testAdapter = TestAdapter(TestData.social.questions,this@TestActivity, applicationContext, socialAnswerList)

                    var layoutManager: LinearLayoutManager = LinearLayoutManager(applicationContext,
                        LinearLayoutManager.VERTICAL, false)

                    recycleView.layoutManager = layoutManager
                    recycleView.adapter = testAdapter


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
        if (currentPage == 1 && buttonPressed == 1 && checkIfAllAnswered()){
            var testAdapter = TestAdapter(TestData.workingworld.questions,this@TestActivity, applicationContext, workingWorldAnswerList)
            recycleView.adapter = testAdapter
            currentPage = 2
            sectionNameTextView.text = "WorkingWorld Settings"
        }
        else if (currentPage == 2 && buttonPressed == 1 && checkIfAllAnswered()){
            var testAdapter = TestAdapter(TestData.studying.questions,this@TestActivity, applicationContext, studyingAnswerList)
            recycleView.adapter = testAdapter
            currentPage = 3
            sectionNameTextView.text = "School Settings"
            bottomNavigationView.menu.getItem(1).setTitle("Submit")

        }else if (currentPage == 3 && buttonPressed == 0 ){
            var testAdapter = TestAdapter(TestData.workingworld.questions,this@TestActivity, applicationContext, workingWorldAnswerList)
            recycleView.adapter = testAdapter
            currentPage = 2
            sectionNameTextView.text = "WorkingWorld Settings"
            bottomNavigationView.menu.getItem(1).setTitle("Next Page")

        }else if (currentPage == 2 && buttonPressed == 0 ){
            var testAdapter = TestAdapter(TestData.social.questions,this@TestActivity, applicationContext, socialAnswerList)
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
        if (currentPage == 1){
            socialAnswerList[position] = answerOption
            Log.d("social", socialAnswerList.toString())
        }else if  (currentPage == 2){
            workingWorldAnswerList[position] = answerOption
            Log.d("workingWorld", workingWorldAnswerList.toString())
        }else{
            studyingAnswerList[position] = answerOption
            Log.d("studying", studyingAnswerList.toString())
        }
    }

    fun checkIfAllAnswered(): Boolean {
        if (currentPage == 1){
            for (item in socialAnswerList){
                if (item == ""){
                    return false
                }
            }
        }else if  (currentPage == 2){
            for (item in workingWorldAnswerList){
                if (item == ""){
                    return false
                }
            }
        }else{
            for (item in studyingAnswerList){
                if (item == ""){
                    return false
                }
            }
        }
        return true

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