package com.example.spendifyApp

import android.content.Context
import android.util.Log
import android.widget.Toast

fun unlockBadge(context: Context, userId: String, badgeId: String) {
    val prefs = context.getSharedPreferences("badges_prefs", Context.MODE_PRIVATE)
    val key = "${userId}_$badgeId"
    val alreadyUnlocked = prefs.getBoolean(key, false)
    Log.d("BadgeUnlock", "Unlocking badge: key=$key alreadyUnlocked=$alreadyUnlocked")
    if (!alreadyUnlocked) {
        prefs.edit().putBoolean(key, true).apply()
        Toast.makeText(context, "🏅 Badge Unlocked!", Toast.LENGTH_SHORT).show()
    }
}