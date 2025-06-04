package com.example.spendifyApp

object BadgeRespository {

    val starterBadges = listOf(
        Badge(
            "getting_started",
            "Getting Started",
            "Opened the app for the first time.",
            iconResId = R.drawable.ic_badge_first_expense
        ),
        Badge(
            "category_creator",
            "Category Creator",
            "Created your first expense category.",
            iconResId = R.drawable.ic_badge_under_budget
        ),
        Badge(
            "first_expense",
            "First Expense",
            "Logged your first expense.",
            iconResId = R.drawable.ic_badge_savings_star
        ),
        Badge(
            "goal_setter",
            "Goal Setter",
            "Set a budget or savings goal.",
            iconResId = R.drawable.ic_goal_setter
        ),
        Badge(
            "summary_viewer",
            "Summary Viewer",
            "Viewed your budget summary.",
            iconResId = R.drawable.ic_badge_summary_viewer
        )
    )
}