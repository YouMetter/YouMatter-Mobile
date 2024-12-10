package com.example.youmatter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.button.MaterialButton
import android.view.animation.Animation
import android.view.animation.AnimationUtils

class OnBoarding : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        var onboardingImage: ImageView = findViewById(R.id.onboardingImage)
        var onboardingTitle: TextView = findViewById(R.id.oboardingTitle)
        var onboardingDescription: TextView = findViewById(R.id.oboardingDescription)
        var nextButton: MaterialButton = findViewById(R.id.nextButton)
        var skipButton: MaterialButton = findViewById(R.id.skipButton)

        var progress1: LinearLayout = findViewById(R.id.onboarding_progress1)
        var progress2: LinearLayout = findViewById(R.id.onboarding_progress2)
        var progress3: LinearLayout = findViewById(R.id.onboarding_progress3)
        var onboardingView: ConstraintLayout = findViewById(R.id.main)
        val fadeOut = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right)
        val fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)

        var currentPage = 1;
        progress2.visibility = View.INVISIBLE
        progress3.visibility = View.INVISIBLE

        skipButton.setOnClickListener{
            val intent = Intent(this@OnBoarding, LoginActivity::class.java)
            startActivity(intent)
        }

        nextButton.setOnClickListener{

            if (currentPage == 3){
                val intent = Intent(this@OnBoarding, LoginActivity::class.java)
                startActivity(intent)
            }else{
                onboardingView.startAnimation(fadeOut)
            }

            fadeOut.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {}

                override fun onAnimationEnd(p0: Animation?) {
                    if (currentPage == 1){
                        progress1.visibility = View.INVISIBLE
                        progress2.visibility = View.VISIBLE
                        onboardingImage.setImageResource(R.drawable.onboarding_image_2)
                        onboardingTitle.text = "Tailored Support at Your Fingertips."
                        onboardingDescription.text = "Whether it’s managing anxiety, anger, or self-doubt, YouMatter provides tools and resources designed just for you. Take interactive tests, track your progress, and build better habits—all in one app."

                        onboardingView.startAnimation(fadeIn)

                        currentPage = 2
                    }else if (currentPage == 2){
                        progress2.visibility = View.INVISIBLE
                        progress3.visibility = View.VISIBLE
                        onboardingImage.setImageResource(R.drawable.onboarding_image_3)
                        onboardingTitle.text = "You’re Never Alone."
                        onboardingDescription.text = "Connect with people who share similar experiences, exchange support, and grow together. YouMatter brings you closer to a community that cares, because you matter."

                        onboardingView.startAnimation(fadeIn)

                        currentPage = 3;
                    }
                }

                override fun onAnimationRepeat(p0: Animation?) {}

            })


        }
    }
}