package com.washingtondcsquad.tudee.presentation.components.deletetask

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.presentation.design.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun ConfirmDeleteTask(

//    deleteOnClick: () -> Unit,
//    cancelOnClick: () -> Unit,

    ) {

    ModalBottomSheet(
        onDismissRequest = {},
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp),
        containerColor = AppTheme.colors.surface,
        sheetState = rememberModalBottomSheetState(),
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .size(width = 32.dp, height = 4.dp)
                    .clip(RoundedCornerShape(100))
                    .background(AppTheme.colors.body),
            )
        },

        ) {

        Column(
            modifier = Modifier.fillMaxWidth(),

            ) {

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
            Spacer(modifier = Modifier.padding(bottom = 24.dp))

            // blur
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(148.dp)
                    .shadow(elevation = 4.dp, spotColor = Color.Black.copy(alpha = 0.08f))
                    .border(BorderStroke(width = 1.dp, AppTheme.colors.surfaceHigh))
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)

            ) {
                DeleteButton(
                    buttonColor = AppTheme.colors.errorVariant,
                    buttonTextId = R.string.delete,
                    buttonTextColor = AppTheme.colors.error,
//                    buttonClick = deleteOnClick
                )
                DeleteButton(
                    borderStroke = 1,
                    borderColor = AppTheme.colors.stroke,
                    buttonTextId = R.string.cancel,
                    buttonTextColor = AppTheme.colors.primary,
//                    buttonClick = cancelOnClick
                )
            }
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
fun DeleteButton(
    borderStroke: Int = 0,
    borderColor: Color = Color.Transparent,
    buttonColor: Color = AppTheme.colors.surfaceHigh,
    buttonTextId: Int,
    buttonTextColor: Color,
//    buttonClick: () -> Unit
) {

    Button(
        onClick = { /*buttonClick()*/ },
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .padding(vertical = 8.dp, horizontal = 24.dp),
        shape = RoundedCornerShape(100.dp),
        colors = ButtonDefaults.buttonColors(buttonColor),
        elevation = null,
        border = BorderStroke(width = borderStroke.dp, borderColor)

    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(

                text = stringResource(buttonTextId),
                style = AppTheme.textStyle.label.large,
                color = buttonTextColor,

                )
        }


    }
}



