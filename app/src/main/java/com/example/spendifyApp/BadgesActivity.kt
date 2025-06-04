package com.example.spendifyApp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spendifyApp.databinding.ActivityBadgesBinding
import com.google.firebase.auth.FirebaseAuth

class BadgesActivity : AppCompatActivity() {
    private lateinit var recyclerBadges: RecyclerView
    private lateinit var badgeAdapter: BadgeAdapter
    private lateinit var binding: ActivityBadgesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBadgesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerBadges = findViewById(R.id.recyclerBadges)

        // Example badges list - replace with your actual data source
        val badges = listOf(
            Badge("summary_viewer", "Summary Viewer", "Viewed your budget summary", isUnlocked = isBadgeUnlocked("summary_viewer"),iconResId = R.drawable.ic_badge_summary_viewer),
            Badge("goal_setter", "Goal Setter", "Set a financial goal", isUnlocked = isBadgeUnlocked("goal_setter"),iconResId = R.drawable.ic_goal_setter),
            Badge("category_creator", "Category Creator", "Created a category", isUnlocked = isBadgeUnlocked("category_creator"),iconResId = R.drawable.ic_badge_under_budget),
            Badge("getting_started", "Getting Started", "Completed the first step", isUnlocked = isBadgeUnlocked("getting_started"),iconResId = R.drawable.ic_badge_first_expense),
            Badge("expense_tracker", "Expense Tracker", "Logged your first expense", isUnlocked = isBadgeUnlocked("expense_tracker"),iconResId = R.drawable.ic_badge_savings_star) // added expense badge
        )

        badgeAdapter = BadgeAdapter(badges)
        recyclerBadges.layoutManager = GridLayoutManager(this, 2) // 2 columns grid
        recyclerBadges.adapter = badgeAdapter

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.Add_Expense -> startActivity(Intent(this, ExpenseActivity::class.java))
                R.id.Home_Button -> startActivity(Intent(this, MainActivity::class.java))
                R.id.Add_Category -> startActivity(Intent(this, CategoryActivity::class.java))
                R.id.Add_Badges -> {
                    // Already on this screen
                    Toast.makeText(this, "You are already on Categories", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }

    private fun isBadgeUnlocked(badgeId: String): Boolean {
        val prefs = getSharedPreferences("badges_prefs", MODE_PRIVATE)
        val userId = getCurrentUserId()
        val key = "${userId}_$badgeId"
        val unlocked = prefs.getBoolean(key, false)
        Log.d("BadgeUnlock", "Checking badge unlock: key=$key unlocked=$unlocked")
        return unlocked
    }


    private fun getCurrentUserId(): String {
        return FirebaseAuth.getInstance().currentUser?.uid ?: ""
    }

    override fun onResume() {
        super.onResume()

        val badges = listOf(
            Badge("summary_viewer", "Summary Viewer", "Viewed your budget summary", isUnlocked = isBadgeUnlocked("summary_viewer"),iconResId = R.drawable.ic_badge_summary_viewer),
            Badge("goal_setter", "Goal Setter", "Set a financial goal", isUnlocked = isBadgeUnlocked("goal_setter"),iconResId = R.drawable.ic_goal_setter),
            Badge("category_creator", "Category Creator", "Created a category", isUnlocked = isBadgeUnlocked("category_creator"),iconResId = R.drawable.ic_badge_under_budget),
            Badge("getting_started", "Getting Started", "Completed the first step", isUnlocked = isBadgeUnlocked("getting_started"),iconResId = R.drawable.ic_badge_first_expense),
            Badge("expense_saver", "Expense Tracker", "Logged your first expense", isUnlocked = isBadgeUnlocked("expense_saver"),iconResId = R.drawable.ic_badge_savings_star)
        )

        badgeAdapter = BadgeAdapter(badges)
        recyclerBadges.adapter = badgeAdapter

        // In onCreate and onResume
        badges.forEach { badge ->
            Log.d("BadgesActivity", "Badge: ${badge.name}, unlocked: ${badge.isUnlocked}")
        }

    }
}