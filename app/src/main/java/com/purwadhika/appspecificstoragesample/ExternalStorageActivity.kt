package com.purwadhika.appspecificstoragesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import com.purwadhika.appspecificstoragesample.databinding.ActivityExternalStorageBinding
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class ExternalStorageActivity : AppCompatActivity() {

    lateinit var binding :ActivityExternalStorageBinding

    val fileName = "ExternalFile.txt"
    val externalFilePath = "MyExternalFile"
    var myExternalFile :File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExternalStorageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "External Storage"

        // check availability external storage
        if (isExternalFileReadOnly() || !isExternalStorageAvailable()){
            binding.buttonSave.isEnabled = false
        }else {
            myExternalFile = File(getExternalFilesDir(externalFilePath), fileName)
            binding.pathFile.text = myExternalFile?.absolutePath
        }

        binding.buttonSave.setOnClickListener {
            saveFileToExternalStorage(binding.inputContentFile.text.toString())
        }

        binding.buttonRead.setOnClickListener {
            readFileExternal()
        }

    }

    private fun saveFileToExternalStorage(contentFile :String){
        val fos = FileOutputStream(myExternalFile)
        fos.use {
            it.write(contentFile.toByteArray())
            Toast.makeText(this@ExternalStorageActivity,
                    "Save file to External Storage success!!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun readFileExternal(){
        val fis = FileInputStream(myExternalFile)
        binding.contentFile.text = fis.bufferedReader().useLines { lines ->
            lines.fold(""){ some, text ->
                "$some$text"
            }
        }
    }

    private fun isExternalFileReadOnly() :Boolean{
        return Environment.MEDIA_MOUNTED_READ_ONLY == Environment.getExternalStorageState()
    }

    private fun isExternalStorageAvailable() :Boolean{
        return Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()
    }
}