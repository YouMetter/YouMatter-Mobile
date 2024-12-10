package com.example.youmatter.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.youmatter.R
import com.example.youmatter.data.model.testDepresi.Questions
import com.example.youmatter.data.model.testDepresi.Request.AnswerOption
import com.google.android.material.button.MaterialButton
import android.content.Context;
import kotlin.reflect.full.memberProperties

class  TestAdapter(private val questionList: ArrayList<Questions>?, private val listener : OnButtonClickListener, private val mContext: Context, private val answerList: ArrayList<Any>): RecyclerView.Adapter<TestAdapter.TestViewHolder>() {

    interface OnButtonClickListener {
        fun onButtonClicked(position: Int, answerOption: AnswerOption)
    }

    class TestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionNumberTextView: TextView = itemView.findViewById(R.id.questionNumber)
        val questionTextView: TextView = itemView.findViewById(R.id.question)
        val option1Button: MaterialButton = itemView.findViewById(R.id.option1)
        val option2Button: MaterialButton = itemView.findViewById(R.id.option2)
        val option3Button: MaterialButton = itemView.findViewById(R.id.option3)
        val option4Button: MaterialButton = itemView.findViewById(R.id.option4)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_test, parent, false)
        return TestViewHolder(view)
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {

        val question = questionList?.get(position)
        holder.questionNumberTextView.text = String.format("%s.",position + 1)
        holder.questionTextView.text = question?.question
        holder.option1Button.text = question?.options?.get(0)?.option
        holder.option2Button.text = question?.options?.get(1)?.option
        holder.option3Button.text = question?.options?.get(2)?.option
        holder.option4Button.text = question?.options?.get(3)?.option

        if (!answerList.isEmpty){
            if (getPropertyByKey(answerList[position], "id") == "1")
            {
                holder.option1Button.strokeColor = ContextCompat.getColorStateList(mContext, R.color.button_outline_color_for_option)
            }else if (getPropertyByKey(answerList[position], "id") == "2"){
                holder.option2Button.strokeColor = ContextCompat.getColorStateList(mContext, R.color.button_outline_color_for_option)
            }else if (getPropertyByKey(answerList[position], "id") == "3"){
                holder.option3Button.strokeColor = ContextCompat.getColorStateList(mContext, R.color.button_outline_color_for_option)
            }else if (getPropertyByKey(answerList[position], "id") == "4"){
                holder.option4Button.strokeColor = ContextCompat.getColorStateList(mContext, R.color.button_outline_color_for_option)
            }
        }

        holder.option1Button.setOnClickListener{
            listener.onButtonClicked(position, AnswerOption(question?.options?.get(0)?.id,question?.options?.get(0)?.option))
            holder.option1Button.strokeColor = ContextCompat.getColorStateList(mContext, R.color.button_outline_color_for_option)
            holder.option2Button.strokeColor = ContextCompat.getColorStateList(mContext, R.color.card_border_color)
            holder.option3Button.strokeColor = ContextCompat.getColorStateList(mContext, R.color.card_border_color)
            holder.option4Button.strokeColor = ContextCompat.getColorStateList(mContext, R.color.card_border_color)

        }
        holder.option2Button.setOnClickListener{
            listener.onButtonClicked(position, AnswerOption(question?.options?.get(1)?.id,question?.options?.get(1)?.option))
            holder.option1Button.strokeColor = ContextCompat.getColorStateList(mContext, R.color.card_border_color)
            holder.option2Button.strokeColor = ContextCompat.getColorStateList(mContext, R.color.button_outline_color_for_option)
            holder.option3Button.strokeColor = ContextCompat.getColorStateList(mContext, R.color.card_border_color)
            holder.option4Button.strokeColor = ContextCompat.getColorStateList(mContext, R.color.card_border_color)
        }
        holder.option3Button.setOnClickListener{
            listener.onButtonClicked(position, AnswerOption(question?.options?.get(2)?.id,question?.options?.get(2)?.option))
            holder.option1Button.strokeColor = ContextCompat.getColorStateList(mContext, R.color.card_border_color)
            holder.option2Button.strokeColor = ContextCompat.getColorStateList(mContext, R.color.card_border_color)
            holder.option3Button.strokeColor = ContextCompat.getColorStateList(mContext, R.color.button_outline_color_for_option)
            holder.option4Button.strokeColor = ContextCompat.getColorStateList(mContext, R.color.card_border_color)
        }
        holder.option4Button.setOnClickListener{
            listener.onButtonClicked(position, AnswerOption(question?.options?.get(3)?.id,question?.options?.get(3)?.option))
            holder.option1Button.strokeColor = ContextCompat.getColorStateList(mContext, R.color.card_border_color)
            holder.option2Button.strokeColor = ContextCompat.getColorStateList(mContext, R.color.card_border_color)
            holder.option3Button.strokeColor = ContextCompat.getColorStateList(mContext, R.color.card_border_color)
            holder.option4Button.strokeColor = ContextCompat.getColorStateList(mContext, R.color.button_outline_color_for_option)
        }
    }

    override fun getItemCount(): Int = questionList?.size!!

    // Function to get a property by key
    fun getPropertyByKey(obj: Any, key: String): Any? {
        // Use reflection to find the property
        val property = obj::class.memberProperties.find { it.name == key }
        return property?.getter?.call(obj) // Get the value of the property
    }
}