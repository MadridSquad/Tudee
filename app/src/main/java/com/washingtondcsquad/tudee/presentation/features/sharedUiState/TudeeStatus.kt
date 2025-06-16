package com.washingtondcsquad.tudee.presentation.features.sharedUiState

import com.washingtondcsquad.tudee.R

enum class TudeeStatus(
    val title: String,
    val icon: Int,
    val bannerImage: Int
) {
    STAY_WORKING(
        title = "Stay working!",
        icon = R.drawable.status_neutral_icon,
        bannerImage = R.drawable.stay_working_status_image
    ),

    DOING_AMAZING(
        title = "Tadaa!",
        icon = R.drawable.status_happy_icon,
        bannerImage = R.drawable.doing_amazing_status_image
    ),

    ZERO_PROGRESS(
        title = "Zero progress?!",
        icon = R.drawable.status_very_sad_icon,
        bannerImage = R.drawable.zero_progress_status_image
    ),

    ZERO_TASK(
        title = "Nothing on your list…",
        icon = R.drawable.status_sad_icon,
        bannerImage = R.drawable.stay_working_status_image
    );

    fun getDescription(completed: Int = 0, total: Int = 0): String {
        return when (this) {
            STAY_WORKING -> "You've completed $completed out of $total tasks. Keep going!"
            DOING_AMAZING -> "You’re doing amazing!!!\nTudee is proud of you."
            ZERO_PROGRESS -> "You’re just scrolling, not working. Tudee is watching. Back to work!!!"
            ZERO_TASK -> "Fill your day with something awesome."
        }
    }
}
