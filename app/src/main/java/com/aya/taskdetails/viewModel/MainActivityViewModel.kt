package com.aya.taskdetails.viewModel

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModel
import com.aya.taskdetails.R
import com.aya.taskdetails.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivityViewModel : ViewModel(), NavigationView.OnNavigationItemSelectedListener ,  View.OnClickListener {

    lateinit var binding: ActivityMainBinding
    lateinit var context: Context
    lateinit var mDrawerToggle: ActionBarDrawerToggle


    private val TAG = "MainActivityViewModel"


    fun Init(
        activityMainBinding: ActivityMainBinding,
        context: Context
    ) {

      this.binding = activityMainBinding
        this.context = context

    }

    fun initDrawerToggle(activity: Activity) {
        mDrawerToggle = object : ActionBarDrawerToggle(
            activity,
            binding.drawerLayout,
            binding.root.findViewById(R.id.toolbar),
            R.string.nav_drawer_open,
            R.string.nav_drawer_close
        ) {

        }
        mDrawerToggle.isDrawerIndicatorEnabled = true
        mDrawerToggle.syncState()
        binding.drawerLayout.addDrawerListener(mDrawerToggle)
    }




    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.search -> {
               Toast.makeText(context,"Search Icon",Toast.LENGTH_LONG)
            }
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.explore -> Toast.makeText(context,"Explore",Toast.LENGTH_LONG)
            R.id.live -> Toast.makeText(context,"Live Chat",Toast.LENGTH_LONG)
            R.id.gallery -> Toast.makeText(context,"Gallery Icon",Toast.LENGTH_LONG)
            R.id.wishlist -> Toast.makeText(context,"Wish List Icon",Toast.LENGTH_LONG)
            R.id.e_magazine -> Toast.makeText(context,"E-Magazine Icon",Toast.LENGTH_LONG)

        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        Log.i(TAG, "onNavigationItemSelected : ${item.title}")
        return true
    }


}