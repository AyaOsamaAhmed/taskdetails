package com.aya.taskdetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

import com.aya.taskdetails.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()  {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  =
            DataBindingUtil.setContentView(this,  R.layout.activity_main)


    }





}