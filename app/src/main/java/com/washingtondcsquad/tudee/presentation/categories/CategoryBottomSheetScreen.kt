package com.washingtondcsquad.tudee.presentation.categories

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.presentation.components.AppTextField
import com.washingtondcsquad.tudee.presentation.components.CancelableActionLayout
import com.washingtondcsquad.tudee.presentation.design.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryBottomSheetScreen(
    modifier: Modifier = Modifier,
    isEditMode: Boolean = false,
    isDeleteMode: Boolean = false,
    bottomSheetTitle: String,
    actionText: String,
    bottomSheetSubTitle: String,
    showBottomSheet: Boolean,
    onDismiss: () -> Unit,
    onSaveCategory: (title: String , categoryIconPathString : String) -> Unit = { _, _ -> }
) {
    if (showBottomSheet) {
        val sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true
        )
        var newCategoryTitle by remember { mutableStateOf("") }
        var categoryIconPath by remember { mutableStateOf("") }
        val borderColor = AppTheme.colors.stroke
        val pickMedia =
            rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                if (uri != null) {
                    categoryIconPath = uri.toString()
                }
            }
        ModalBottomSheet(
            scrimColor = Color(0x99000000),
            dragHandle = {
                BottomSheetDefaults.DragHandle(
                    color = AppTheme.colors.body
                )
            },
            onDismissRequest = { onDismiss() },
            sheetState = sheetState,
            containerColor = AppTheme.colors.surfaceHigh,
            modifier = modifier
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row ( modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text =bottomSheetTitle,
                        style = AppTheme.textStyle.title.large,
                        color = AppTheme.colors.title,
                    )
                    if (isEditMode){
                    Text(
                        text = "Delete",
                        style = AppTheme.textStyle.label.large,
                        color = AppTheme.colors.error,
                        modifier = Modifier
                    )
                        }
                }
                if (!isDeleteMode) {
                    AppTextField(
                        prefixIconPainter = painterResource(R.drawable.add_new_category),
                        hintText = "Category Title",
                        onValueChange = { newCategoryTitle = it },
                        value = newCategoryTitle,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = bottomSheetSubTitle,
                    style = AppTheme.textStyle.label.medium,
                    color = AppTheme.colors.title
                )
                if (isDeleteMode) {
                    Image(

                        painter = painterResource(R.drawable.delete_robot),
                        contentDescription = "Delete",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                           .size(107.dp, 100.dp)
                            .padding(horizontal = 16.dp)
                    )
                }
                else {
                    if (categoryIconPath.isEmpty()) {
                        Icon(
                            painter = painterResource(R.drawable.add_new_image),
                            contentDescription = "Category Image",
                            tint = AppTheme.colors.hint,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .clickable {
                                    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                                }
                                .drawBehind {
                                    val stroke = Stroke(
                                        width = 1.dp.toPx(),
                                        pathEffect = PathEffect.dashPathEffect(
                                            floatArrayOf(
                                                10f,
                                                10f
                                            )
                                        )
                                    )
                                    drawRoundRect(
                                        color = borderColor,
                                        cornerRadius = CornerRadius(16.dp.toPx()),
                                        style = stroke
                                    )
                                }
                                .padding(32.dp)
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)

                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(categoryIconPath),
                                contentDescription = "Category Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(16.dp))
                                    .border(
                                        width = 1.dp,
                                        color = AppTheme.colors.stroke,
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                    .size(115.dp)
                            )
                            Icon(
                                painter = painterResource(R.drawable.pencil),
                                contentDescription = "Category Image",
                                tint = AppTheme.colors.secondary,
                                modifier = Modifier
                                    .align(
                                        Alignment.Center
                                    )
                                    .background(
                                        color = AppTheme.colors.surfaceHigh,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .padding(6.dp)
                                    .clickable {
                                        pickMedia.launch(
                                            PickVisualMediaRequest(
                                                ActivityResultContracts.PickVisualMedia.ImageOnly
                                            )
                                        )
                                    }
                            )
                        }
                    }
                }


                CancelableActionLayout(
                    modifier = Modifier,
                    actionText = actionText,
                    actionTextColor = if(isDeleteMode) AppTheme.colors.error else AppTheme.colors.onPrimary,
                    actionBackgroundColor =if(isDeleteMode) listOf( AppTheme.colors.errorVariant, AppTheme.colors.errorVariant) else AppTheme.colors.primaryGradient,
                    isEnabled = if(isDeleteMode) true else categoryIconPath.isNotEmpty() && newCategoryTitle.isNotEmpty(),
                    onCancel = { onDismiss() },
                    onAction = {
                        onSaveCategory(newCategoryTitle, categoryIconPath)
                        onDismiss()
                    }
                )

            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CategoryBottomSheetScreenPreview() {
    AppTheme(true) {
        CategoryBottomSheetScreen(
            bottomSheetTitle = "Add New Category",
            bottomSheetSubTitle = "Category Image",
            modifier = Modifier,
            isEditMode = false,
            isDeleteMode = false,
            showBottomSheet = true,
            actionText = "Add",
            onDismiss = {}
        )
    }
}