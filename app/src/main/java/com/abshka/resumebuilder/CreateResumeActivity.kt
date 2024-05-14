package com.abshka.resumebuilder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class CreateResumeActivity : AppCompatActivity() {
    private lateinit var fullNameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var genderEditText: EditText
    private lateinit var maritalStatusEditText: EditText
    private lateinit var desiredPositionEditText: EditText
    private lateinit var desiredSalaryEditText: EditText
    private lateinit var educationEditText: EditText
    private lateinit var careerEditText: EditText
    private lateinit var skillsEditText: EditText
    private lateinit var additionalInfoEditText: EditText
    private lateinit var createResumeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_resume)

        fullNameEditText = findViewById(R.id.fullNameEditText)
        ageEditText = findViewById(R.id.ageEditText)
        genderEditText = findViewById(R.id.genderEditText)
        maritalStatusEditText = findViewById(R.id.maritalStatusEditText)
        desiredPositionEditText = findViewById(R.id.desiredPositionEditText)
        desiredSalaryEditText = findViewById(R.id.desiredSalaryEditText)
        educationEditText = findViewById(R.id.educationEditText)
        careerEditText = findViewById(R.id.careerEditText)
        skillsEditText = findViewById(R.id.skillsEditText)
        additionalInfoEditText = findViewById(R.id.additionalInfoEditText)
        createResumeButton = findViewById(R.id.createResumeButton)

        createResumeButton.setOnClickListener {
            val fullName = fullNameEditText.text.toString()
            val age = ageEditText.text.toString()
            val gender = genderEditText.text.toString()
            val maritalStatus = maritalStatusEditText.text.toString()
            val desiredPosition = desiredPositionEditText.text.toString()
            val desiredSalary = desiredSalaryEditText.text.toString()
            val education = educationEditText.text.toString()
            val career = careerEditText.text.toString()
            val skills = skillsEditText.text.toString()
            val additionalInfo = additionalInfoEditText.text.toString()

            val intent = Intent(this, ResumeActivity::class.java)
            intent.putExtra("fullName", fullName)
            intent.putExtra("age", age)
            intent.putExtra("gender", gender)
            intent.putExtra("maritalStatus", maritalStatus)
            intent.putExtra("desiredPosition", desiredPosition)
            intent.putExtra("desiredSalary", desiredSalary)
            intent.putExtra("education", education)
            intent.putExtra("career", career)
            intent.putExtra("skills", skills)
            intent.putExtra("additionalInfo", additionalInfo)
            startActivity(intent)
        }
    }
}