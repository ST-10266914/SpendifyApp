package com.example.spendifyApp

data class Badge(
    val id: String,
    val name: String,
    val description: String,
    var isUnlocked: Boolean = false,
    var dateUnlocked: String? = null,
    val iconResId: Int
)
