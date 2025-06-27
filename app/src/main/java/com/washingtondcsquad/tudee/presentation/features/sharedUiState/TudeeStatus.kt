package com.washingtondcsquad.tudee.presentation.features.sharedUiState

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.washingtondcsquad.tudee.R

enum class TudeeStatus(

    val icon: Int,
    val bannerImage: Int
) {
    STAY_WORKING(
        icon = R.drawable.status_neutral_icon,
        bannerImage = R.drawable.stay_working_status_image
    ),

    DOING_AMAZING(
        icon = R.drawable.status_happy_icon,
        bannerImage = R.drawable.doing_amazing_status_image
    ),

    ZERO_PROGRESS(
        icon = R.drawable.status_very_sad_icon,
        bannerImage = R.drawable.zero_progress_status_image
    ),

    ZERO_TASK(

        icon = R.drawable.status_sad_icon,
        bannerImage = R.drawable.stay_working_status_image
    );
   @Composable
    fun getDescription(completed: Int = 0, total: Int = 0): String {
        return when (this) {
            STAY_WORKING -> "${stringResource(R.string.you_ve_completed)} $completed ${stringResource(R.string.out_of)} $total ${stringResource(R.string.tasks_Keep_going)}"
            DOING_AMAZING -> stringResource(R.string.you_re_doing_amazing_tudee_is_proud_of_you)
            ZERO_PROGRESS -> stringResource(R.string.you_re_just_scrolling_not_working_tudee_is_watching_back_to_work)
            ZERO_TASK -> stringResource(R.string.fill_your_day_with_something_awesome)
        }
    }
    @Composable
    fun getTitle(): String {
        return when (this) {
            STAY_WORKING -> stringResource(R.string.stay_working)
            DOING_AMAZING -> stringResource(R.string.tadaa)
            ZERO_PROGRESS -> stringResource(R.string.zero_progress)
            ZERO_TASK -> stringResource(R.string.nothing_on_your_list)
        }
    }
}
