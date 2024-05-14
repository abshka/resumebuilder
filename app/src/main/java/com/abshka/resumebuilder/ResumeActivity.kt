package com.abshka.resumebuilder

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResumeActivity : AppCompatActivity() {
    private lateinit var fullNameTextView: TextView
    private lateinit var ageTextView: TextView
    private lateinit var genderTextView: TextView
    private lateinit var maritalStatusTextView: TextView
    private lateinit var desiredPositionTextView: TextView
    private lateinit var desiredSalaryTextView: TextView
    private lateinit var educationTextView: TextView
    private lateinit var careerTextView: TextView
    private lateinit var skillsTextView: TextView
    private lateinit var additionalInfoTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resume)

        fullNameTextView = findViewById(R.id.fullNameTextView)
        ageTextView = findViewById(R.id.ageTextView)
        genderTextView = findViewById(R.id.genderTextView)
        maritalStatusTextView = findViewById(R.id.maritalStatusTextView)
        desiredPositionTextView = findViewById(R.id.desiredPositionTextView)
        desiredSalaryTextView = findViewById(R.id.desiredSalaryTextView)
        educationTextView = findViewById(R.id.educationTextView)
        careerTextView = findViewById(R.id.careerTextView)
        skillsTextView = findViewById(R.id.skillsTextView)
        additionalInfoTextView = findViewById(R.id.additionalInfoTextView)

        val intent = intent
        fullNameTextView.text = "ФИО: ${intent.getStringExtra("fullName")}"
        ageTextView.text = "Возраст: ${intent.getStringExtra("age")}"
        genderTextView.text = "Пол: ${intent.getStringExtra("gender")}"
        maritalStatusTextView.text = "Семейное положение: ${intent.getStringExtra("maritalStatus")}"
        desiredPositionTextView.text = "Желаемая должность: ${intent.getStringExtra("desiredPosition")}"
        desiredSalaryTextView.text = "Желаемая зарплата: ${intent.getStringExtra("desiredSalary")}"
        educationTextView.text = "Образование: ${intent.getStringExtra("education")}"
        careerTextView.text = "Карьера: ${intent.getStringExtra("career")}"
        skillsTextView.text = "Навыки: ${intent.getStringExtra("skills")}"
        additionalInfoTextView.text = "Дополнительная информация: ${intent.getStringExtra("additionalInfo")}"
    }
}