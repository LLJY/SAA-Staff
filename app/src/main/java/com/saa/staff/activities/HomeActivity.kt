package com.saa.staff.activities

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.navigation.NavigationView
import com.saa.staff.R
import com.saa.staff.databinding.ActivityHomeBinding
import com.saa.staff.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    lateinit var binding: ActivityHomeBinding
    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // set the userid inside homeViewModel
        viewModel.userId =
            intent.getStringExtra("USER_ID")!! // let the app crash if something does go wrong.
        viewModel.userType = intent.getIntExtra("USER_TYPE", 0)
        var menuItems = mutableListOf(
            R.id.editProfileFragment,
            R.id.manageCoursesFragment,
            R.id.manageFellowshipFragment,
            R.id.manageScholarshipFragment,
            R.id.manageDiplomaFragment,
            R.id.sendNotificationFragment,
            R.id.trainingProgressFragment,
            R.id.reviewApplicationFragment,
            R.id.approveStaffFragment
        )
        Log.i("user_type", viewModel.userType.toString())
        // 0 school head, 1 course manager 2, admin
        when (viewModel.userType) {
            0 -> {
                binding.navigationView.menu.removeGroup(R.id.admin_group)
                binding.navigationView.menu.removeGroup(R.id.course_manager_group)
            }
            1 -> {
                binding.navigationView.menu.removeGroup(R.id.admin_group)
                binding.navigationView.menu.removeGroup(R.id.school_head_group)
            }
            2 -> {
                binding.navigationView.menu.removeGroup(R.id.course_manager_group)
                binding.navigationView.menu.removeGroup(R.id.school_head_group)
            }
        }
        // setup the navcontroller
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            menuItems.toSet(), binding.drawerLayout
        )
        setupActionBarWithNavController(navController, binding.drawerLayout)
        binding.navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        item.isChecked = true
        binding.drawerLayout.closeDrawers()
        when (item.itemId) {
            R.id.edit_profile_item -> navController.navigate(R.id.editProfileFragment)
            R.id.manage_course_item -> navController.navigate(R.id.manageCoursesFragment)
            R.id.manage_fellowship_item -> navController.navigate(R.id.manageFellowshipFragment)
            R.id.manage_scholarship_item -> navController.navigate(R.id.manageScholarshipFragment)
            R.id.manage_diploma_item -> navController.navigate(R.id.manageDiplomaFragment)
            R.id.send_notification_item -> navController.navigate(R.id.sendNotificationFragment)
            R.id.training_progress_item -> navController.navigate(R.id.trainingProgressFragment)
            R.id.review_application_item -> navController.navigate(R.id.reviewApplicationFragment)
            R.id.approve_staff_item -> navController.navigate(R.id.approveStaffFragment)
            R.id.logout_button -> {
                val dialog = AlertDialog.Builder(this@HomeActivity)
                dialog.setTitle("Are you sure?")
                dialog.setMessage("Are you sure you want to logout?")
                dialog.setNegativeButton("NO") { _: DialogInterface, i: Int ->

                }
                dialog.setPositiveButton("YES") { dialog: DialogInterface, i: Int ->
                    dialog.dismiss()
                    // disable the callback when yes is pressed and trigger onBackPressed
                    val intent = Intent(this@HomeActivity, MainActivity::class.java)
                    startActivity(intent)
                }
                dialog.show()
            }
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

}