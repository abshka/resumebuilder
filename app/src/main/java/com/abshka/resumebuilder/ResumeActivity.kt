package com.abshka.resumebuilder

import android.content.ContentValues
import android.content.Context
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import java.io.OutputStream

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
    private lateinit var savePdfButton: Button

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
        savePdfButton = findViewById(R.id.savePdfButton)

        val intent = intent
        fullNameTextView.text = intent.getStringExtra("fullName")
        ageTextView.text = intent.getStringExtra("age")
        genderTextView.text = intent.getStringExtra("gender")
        maritalStatusTextView.text = intent.getStringExtra("maritalStatus")
        desiredPositionTextView.text = intent.getStringExtra("desiredPosition")
        desiredSalaryTextView.text = intent.getStringExtra("desiredSalary")
        educationTextView.text = intent.getStringExtra("education")
        careerTextView.text = intent.getStringExtra("career")
        skillsTextView.text = intent.getStringExtra("skills")
        additionalInfoTextView.text = intent.getStringExtra("additionalInfo")

        savePdfButton.setOnClickListener {
            saveResumeAsPdf()
        }
    }

    private fun saveResumeAsPdf() {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = pdfDocument.startPage(pageInfo)

        val canvas = page.canvas
        val paint = android.graphics.Paint()
        paint.textSize = 12f

        // Выбор варианта верстки
        // drawResumeVariant1(canvas, paint)
         drawResumeVariant2(canvas, paint)
        // drawResumeVariant3(canvas, paint)

        pdfDocument.finishPage(page)

        val fileName = "resume.pdf"
        val outputStream: OutputStream?

        try {
            outputStream = getOutputStream(this, fileName)
            if (outputStream != null) {
                pdfDocument.writeTo(outputStream)
                Toast.makeText(this, "PDF сохранен", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Ошибка сохранения PDF", Toast.LENGTH_SHORT).show()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Ошибка сохранения PDF: ${e.message}", Toast.LENGTH_SHORT).show()
        } finally {
            pdfDocument.close()
        }
    }

    private fun getOutputStream(context: Context, fileName: String): OutputStream? {
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf")
            put(MediaStore.MediaColumns.RELATIVE_PATH, "Documents/")
        }

        val contentResolver = context.contentResolver
        val uri: Uri? = contentResolver.insert(MediaStore.Files.getContentUri("external"), contentValues)
        return uri?.let { contentResolver.openOutputStream(it) }
    }

    private fun drawResumeVariant1(canvas: android.graphics.Canvas, paint: android.graphics.Paint) {
        var y = 25f
        canvas.drawText(fullNameTextView.text.toString(), 10f, y, paint)
        y += paint.descent() - paint.ascent()
        canvas.drawText(ageTextView.text.toString(), 10f, y, paint)
        y += paint.descent() - paint.ascent()
        canvas.drawText(genderTextView.text.toString(), 10f, y, paint)
        y += paint.descent() - paint.ascent()
        canvas.drawText(maritalStatusTextView.text.toString(), 10f, y, paint)
        y += paint.descent() - paint.ascent()
        canvas.drawText(desiredPositionTextView.text.toString(), 10f, y, paint)
        y += paint.descent() - paint.ascent()
        canvas.drawText(desiredSalaryTextView.text.toString(), 10f, y, paint)
        y += paint.descent() - paint.ascent()
        canvas.drawText(educationTextView.text.toString(), 10f, y, paint)
        y += paint.descent() - paint.ascent()
        canvas.drawText(careerTextView.text.toString(), 10f, y, paint)
        y += paint.descent() - paint.ascent()
        canvas.drawText(skillsTextView.text.toString(), 10f, y, paint)
        y += paint.descent() - paint.ascent()
        canvas.drawText(additionalInfoTextView.text.toString(), 10f, y, paint)
    }

    private fun drawResumeVariant2(canvas: android.graphics.Canvas, paint: android.graphics.Paint) {
        val startX = 10f
        val startY = 25f
        val lineHeight = paint.descent() - paint.ascent()
        var y = startY

        paint.textSize = 14f
        paint.isFakeBoldText = true
        canvas.drawText("Резюме", startX, y, paint)
        y += lineHeight * 2

        paint.textSize = 12f
        paint.isFakeBoldText = false
        canvas.drawText("ФИО: ${fullNameTextView.text}", startX, y, paint)
        y += lineHeight
        canvas.drawText("Возраст: ${ageTextView.text}", startX, y, paint)
        y += lineHeight
        canvas.drawText("Пол: ${genderTextView.text}", startX, y, paint)
        y += lineHeight
        canvas.drawText("Семейное положение: ${maritalStatusTextView.text}", startX, y, paint)
        y += lineHeight
        canvas.drawText("Желаемая должность: ${desiredPositionTextView.text}", startX, y, paint)
        y += lineHeight
        canvas.drawText("Желаемая зарплата: ${desiredSalaryTextView.text}", startX, y, paint)
        y += lineHeight
        canvas.drawText("Образование: ${educationTextView.text}", startX, y, paint)
        y += lineHeight
        canvas.drawText("Карьера: ${careerTextView.text}", startX, y, paint)
        y += lineHeight
        canvas.drawText("Навыки: ${skillsTextView.text}", startX, y, paint)
        y += lineHeight
        canvas.drawText("Дополнительная информация: ${additionalInfoTextView.text}", startX, y, paint)
    }

    private fun drawResumeVariant3(canvas: android.graphics.Canvas, paint: android.graphics.Paint) {
        val startX = 10f
        val startY = 25f
        val lineHeight = paint.descent() - paint.ascent()
        var y = startY

        paint.textSize = 14f
        paint.isFakeBoldText = true
        canvas.drawText("Резюме", startX, y, paint)
        y += lineHeight * 2

        paint.textSize = 12f
        paint.isFakeBoldText = false
        canvas.drawText("ФИО: ${fullNameTextView.text}", startX, y, paint)
        y += lineHeight

        paint.isFakeBoldText = true
        canvas.drawText("Личные данные", startX, y, paint)
        y += lineHeight
        paint.isFakeBoldText = false
        canvas.drawText("Возраст: ${ageTextView.text}", startX + 20, y, paint)
        y += lineHeight
        canvas.drawText("Пол: ${genderTextView.text}", startX + 20, y, paint)
        y += lineHeight
        canvas.drawText("Семейное положение: ${maritalStatusTextView.text}", startX + 20, y, paint)
        y += lineHeight

        paint.isFakeBoldText = true
        canvas.drawText("Желаемая должность", startX, y, paint)
        y += lineHeight
        paint.isFakeBoldText = false
        canvas.drawText("Должность: ${desiredPositionTextView.text}", startX + 20, y, paint)
        y += lineHeight
        canvas.drawText("Зарплата: ${desiredSalaryTextView.text}", startX + 20, y, paint)
        y += lineHeight

        paint.isFakeBoldText = true
        canvas.drawText("Образование", startX, y, paint)
        y += lineHeight
        paint.isFakeBoldText = false
        canvas.drawText("${educationTextView.text}", startX + 20, y, paint)
        y += lineHeight

        paint.isFakeBoldText = true
        canvas.drawText("Карьера", startX, y, paint)
        y += lineHeight
        paint.isFakeBoldText = false
        canvas.drawText("${careerTextView.text}", startX + 20, y, paint)
        y += lineHeight

        paint.isFakeBoldText = true
        canvas.drawText("Навыки", startX, y, paint)
        y += lineHeight
        paint.isFakeBoldText = false
        canvas.drawText("${skillsTextView.text}", startX + 20, y, paint)
        y += lineHeight

        paint.isFakeBoldText = true
        canvas.drawText("Дополнительная информация", startX, y, paint)
        y += lineHeight
        paint.isFakeBoldText = false
        canvas.drawText("${additionalInfoTextView.text}", startX + 20, y, paint)
    }
}