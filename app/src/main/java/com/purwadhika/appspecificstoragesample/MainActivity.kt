package com.purwadhika.appspecificstoragesample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.purwadhika.appspecificstoragesample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding :ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGotoInternal.setOnClickListener {
            startActivity(Intent(this@MainActivity, InternalStorageActivity::class.java))
        }

        binding.buttonGotoExternal.setOnClickListener {
            startActivity(Intent(this@MainActivity, ExternalStorageActivity::class.java))
        }
    }
}