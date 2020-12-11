package com.purwadhika.appspecificstoragesample

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.purwadhika.appspecificstoragesample.databinding.ActivityInternalStorageBinding
import java.io.File

class InternalStorageActivity : AppCompatActivity() {

    lateinit var binding :ActivityInternalStorageBinding

    private val filename = "internalSaveFile.txt"
    lateinit var file :File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternalStorageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()

        file = File(filesDir, filename)

        binding.pathFile.text = file.absolutePath

        binding.buttonSave.setOnClickListener {
            // get input string from EditText then save to internal storage
            saveFileInternal(binding.inputContentFile.text.toString())
        }

        binding.buttonRead.setOnClickListener {
            getFileInternal()
        }

    }

    private fun setupToolbar(){
        supportActionBar?.title = "Internal Storage"
    }

    private fun saveFileInternal(contentText :String){
        openFileOutput(filename, Context.MODE_PRIVATE).use {
            it.write(contentText.toByteArray())
            Toast.makeText(this@InternalStorageActivity, "Save file success!!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getFileInternal(){
        val contentText = openFileInput(filename).bufferedReader().useLines { lines ->
            lines.fold(""){some, text ->
                "$some $text"
            }
        }
        binding.contentFile.text = contentText
    }

}