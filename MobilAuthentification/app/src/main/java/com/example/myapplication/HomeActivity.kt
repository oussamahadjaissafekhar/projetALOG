package com.example.projet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityHomeBinding
import com.example.myapplication.model.MyModel

class HomeActivity : AppCompatActivity() {
    lateinit var navController: NavController
    private lateinit var binding: ActivityHomeBinding
    private lateinit var myModel: MyModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        myModel = ViewModelProvider(this).get(MyModel::class.java)
        if (intent != null && intent.hasExtra("ClientId")) {
            val value = intent.getStringExtra("ClientId")
            Log.d("ClientId", value.toString())
            myModel.ClientId = value!!.toInt()
        }
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.navBottom, navController)


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.overflow_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.logout -> {
                // Add Logout code here
            }

        }
        return super.onOptionsItemSelected(item)

    }


}