package com.aya.taskdetails

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProviders

import com.aya.taskdetails.databinding.ActivityMainBinding
import com.aya.taskdetails.viewModel.MainActivityViewModel
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener , View.OnClickListener     {


    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    private val drawerLayout: DrawerLayout? = null
    private val nav: NavigationView? = null
    private val mDrawerToggle: ActionBarDrawerToggle? = null
    private val toolbar: Toolbar? = null
    private lateinit var searchIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding  =
            DataBindingUtil.setContentView(this,  R.layout.activity_main)

        searchIcon = findViewById<ImageView>(R.id.search)
        searchIcon.setOnClickListener(this)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.Init(binding, this)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        viewModel.initDrawerToggle(this)


        binding.navView.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
          return  viewModel.onNavigationItemSelected(item)
    }

    override fun onClick(v: View?) {
        viewModel.onClick(v)
    }


}