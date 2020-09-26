package com.saa.staff.activities.mainui

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
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
        // setup the navcontroller
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.editProfileFragment,
                R.id.manageCoursesFragment,
                R.id.manageFellowshipFragment,
                R.id.manageScholarshipFragment,
                R.id.manageDiplomaFragment,
                R.id.sendNotificationFragment,
                R.id.trainingProgressFragment,
                R.id.reviewApplicationFragment,
                R.id.approveStaffFragment
            ), binding.drawerLayout
        )
        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)
        NavigationUI.setupWithNavController(binding.navigationView, navController)
        binding.navigationView.setNavigationItemSelectedListener(this)

        // set the userid inside homeViewModel
        viewModel.userId = intent.getStringExtra("USER_ID")

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        item.isChecked = true
        binding.drawerLayout.closeDrawers()
        when (item.itemId){
            R.id.edit_profile_item -> navController.navigate(R.id.editProfileFragment)
            R.id.manage_course_item -> navController.navigate(R.id.manageCoursesFragment)
            R.id.manage_fellowship_item -> navController.navigate(R.id.manageFellowshipFragment)
            R.id.manage_scholarship_item -> navController.navigate(R.id.manageScholarshipFragment)
            R.id.manage_diploma_item -> navController.navigate(R.id.manageDiplomaFragment)
            R.id.send_notification_item -> navController.navigate(R.id.sendNotificationFragment)
            R.id.training_progress_item -> navController.navigate(R.id.trainingProgressFragment)
            R.id.review_application_item -> navController.navigate(R.id.reviewApplicationFragment)
            R.id.approve_staff_item -> navController.navigate(R.id.approveStaffFragment)
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

}