package com.example.imagesearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.imagesearch.Fragments.InsertKotlinFrag
import com.example.imagesearch.Fragments.ResultsKotlinFrag
import com.example.imagesearch.Fragments.SavedResultKotlinFrag
import com.example.imagesearch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fragment: Fragment




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragment= InsertKotlinFrag()
        supportFragmentManager.beginTransaction().replace(R.id.container,fragment).commit()

        binding.insertBtn.setOnClickListener {
           fragment= InsertKotlinFrag()
            supportFragmentManager.beginTransaction().replace(R.id.container,fragment).commit()

        }

        binding.resultBtn.setOnClickListener {
            fragment= ResultsKotlinFrag()
            supportFragmentManager.beginTransaction().replace(R.id.container,fragment).commit()
        }

        binding.savedResultBtn.setOnClickListener {
            fragment= SavedResultKotlinFrag()
            supportFragmentManager.beginTransaction().replace(R.id.container,fragment).commit()
        }
    }
}