package com.example.practice_send_data_from_fragment_to_fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practice_send_data_from_fragment_to_fragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        viewBinding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        //기본 화면(MainFragment) 세팅
        supportFragmentManager
            .beginTransaction()
            .replace(viewBinding.frameLayout.id, MainFragment())
            .commitAllowingStateLoss()

    }
}