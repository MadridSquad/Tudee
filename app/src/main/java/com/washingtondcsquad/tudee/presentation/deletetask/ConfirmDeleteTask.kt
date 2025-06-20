package com.washingtondcsquad.tudee.presentation.deletetask

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.presentation.components.CancelableActionLayout
import com.washingtondcsquad.tudee.presentation.design.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmDeleteTask(
    deleteOnClick: () -> Unit,
    cancelOnClick: () -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    LaunchedEffect(Unit) {
        sheetState.show()
    }

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp),
        containerColor = AppTheme.colors.surface,
        sheetState = sheetState,
        dragHandle = null
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),

            ) {

            CustomDragHandle(modifier = Modifier.padding(vertical = 16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),

                ) {

                DeleteTaskTitle()
                DeleteTaskDescription()
                DeleteTaskIllustration()

            }
            // blur
            CancelableActionLayout(
                actionText = stringResource(R.string.delete),
                actionTextColor = AppTheme.colors.error,
                onAction = { deleteOnClick() },
                onCancel = { cancelOnClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .height(148.dp)
                    .shadow(elevation = 20.dp, spotColor = Color.Black.copy(alpha = 0.08f)),
                actionBackgroundColor = List(2) { AppTheme.colors.errorVariant },
                isEnabled = true
            )
        }
    }

}

@Composable
fun DeleteTaskTitle() {

    Text(

        text = stringResource(R.string.delete_task),
        style = AppTheme.textStyle.title.large,

        )
}

@Composable
fun DeleteTaskDescription() {

    Text(

        text = stringResource(R.string.are_you_sure_to_continue),
        style = AppTheme.textStyle.body.large,

        )
}

@Composable
fun DeleteTaskIllustration() {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.delete_task_illustration),
            contentDescription = "Robot: Are you sure to delete task",
            modifier = Modifier
                .size(width = 107.dp, height = 100.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop

        )
    }
}

@Composable
fun CustomDragHandle(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(width = 32.dp, height = 4.dp)
                .clip(RoundedCornerShape(100))
                .background(AppTheme.colors.body.copy(alpha = AppTheme.colors.body.alpha * 0.4f))
        )
    }
}



