package com.example.youmatter.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youmatter.MockCall
import com.example.youmatter.TestActivity
import com.example.youmatter.data.model.articleLists.ArticleListsResponse
import com.example.youmatter.data.model.userProfile.Data
import com.example.youmatter.data.model.userProfile.UserProfileResponse
import com.example.youmatter.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        username()
        articles()

        binding.anxietyTest.setOnClickListener{
            val intent = Intent(requireContext(), TestActivity::class.java)
            startActivity(intent)
        }
    }

    private fun username(){
        val mockResponse = UserProfileResponse(true, data = Data("Hakim","Hakim"),"success")
        val mockCall = MockCall(mockResponse)

        mockCall.enqueue(object : Callback<UserProfileResponse> {
            override fun onResponse(
                call: Call<UserProfileResponse?>,
                response: Response<UserProfileResponse?>
            ) {
                if (response.body()?.success == true){
                    binding.username.setText("Hi, " + response.body()?.data?.username)
                }
            }

            override fun onFailure(
                call: Call<UserProfileResponse?>,
                t: Throwable
            ) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun articles(){
        val mockResponse = ArticleListsResponse(true, arrayListOf(com.example.youmatter.data.model.articleLists.Data(
            "","","Anxiety Menyerang, Bagaimana Cara untuk Meredakannya?", "lorem ipsum dolor sit amet", "https://d1vbn70lmn1nqe.cloudfront.net/prod/wp-content/uploads/2023/08/21045056/anxiety-menyerang-bagaimana-cara-untuk-meredakannya-halodoc.jpg.webp"),
            com.example.youmatter.data.model.articleLists.Data(
                "","","Anxiety Menyerang, Bagaimana Cara untuk Meredakannya?", "lorem ipsum dolor sit amet", "https://d1vbn70lmn1nqe.cloudfront.net/prod/wp-content/uploads/2023/08/21045056/anxiety-menyerang-bagaimana-cara-untuk-meredakannya-halodoc.jpg.webp")
        ))
        val mockCall = MockCall(mockResponse)

        mockCall.enqueue(object : Callback<ArticleListsResponse> {
            override fun onResponse(
                call: Call<ArticleListsResponse?>,
                response: Response<ArticleListsResponse?>
            ) {
                if (response.body()?.success == true) {
                    var articleAdapter = ArticleAdapter(response.body()?.data, requireContext())
                    var layoutManager: LinearLayoutManager = LinearLayoutManager(requireContext(),
                        LinearLayoutManager.HORIZONTAL, false)
                    binding.recycleView.layoutManager = layoutManager
                    binding.recycleView.adapter = articleAdapter
                }
            }

            override fun onFailure(
                call: Call<ArticleListsResponse?>,
                t: Throwable
            ) {
                TODO("Not yet implemented")
            }

        })
    }



}

