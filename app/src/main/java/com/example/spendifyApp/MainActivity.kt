package com.example.spendifyApp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.example.spendifyApp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseAuth = FirebaseAuth.getInstance()

        // Redirect to SignInActivity if not signed in
        if (firebaseAuth.currentUser == null) {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        } else {
            // Show welcome message

        }

        // Logout button functionality
        binding.buttonLogout.setOnClickListener {
            firebaseAuth.signOut()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
        // Redirect to CategoryActivity
//        binding.buttonManageCategories.setOnClickListener {
//            val intent = Intent(this, CategoryActivity::class.java)
//            startActivity(intent)
//        }

        val goalButton = findViewById<Button>(R.id.buttonSetGoals)
        goalButton.setOnClickListener {
            val intent = Intent(this, GoalSettingsActivity::class.java)
            startActivity(intent)
        }

        // Redirect to CategoryGraphActivity (new)
        binding.buttonShowCategoryGraph.setOnClickListener {
            val intent = Intent(this, CategoryGraphActivity::class.java)
            startActivity(intent)
        }

        binding.buttonBalanceOverview.setOnClickListener {
            val intent = Intent(this, BalanceOverviewActivity::class.java)
            startActivity(intent)
        }

//        binding.buttonViewBadges.setOnClickListener {
//            val intent = Intent(this, BadgesActivity::class.java)
//            startActivity(intent)
//        }

        binding.bottomNavigationView.selectedItemId = R.id.Home_Button
        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.Add_Expense -> startActivity(Intent(this, ExpenseActivity::class.java))
                R.id.Home_Button -> {
                    // Already on home, do nothing or show toast
                    Toast.makeText(this, "You are already on Home", Toast.LENGTH_SHORT).show()
                }
                R.id.Add_Category-> startActivity(Intent(this, CategoryActivity::class.java))
                R.id.Add_Badges -> startActivity(Intent(this, BadgesActivity::class.java))
                else -> {

                }
            }
            true
        }

    }


}