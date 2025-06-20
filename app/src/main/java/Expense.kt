package com.example.spendifyApp

data class Expense(
    val id: String = "",
    val amount: Double = 0.0,
    val description: String = "",
    val date: String = "",
    val category: String = "", // this is category ID
    val imageUrl: String? = null,
    val userId: String = ""
)
