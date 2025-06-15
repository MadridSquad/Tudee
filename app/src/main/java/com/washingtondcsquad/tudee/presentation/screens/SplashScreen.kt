import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.washingtondcsquad.tudee.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.washingtondcsquad.tudee.presentation.design.textStyle.Cherry_Bomb
import com.washingtondcsquad.tudee.presentation.design.theme.AppTheme

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    title: String,
){
    val strokeInDp = 4.dp
    val localDensity = LocalDensity.current
    val strokeInPx  = with(localDensity){
        strokeInDp.toPx()
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colors.surface),
        contentAlignment = Alignment.Center

    ){
       Image(
            painter = if (isSystemInDarkTheme()) painterResource(R.drawable.background_graphics_dark) else painterResource(R.drawable.background_graphics) ,
            contentDescription = "Background graphics",
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.overlay),
            alignment = Alignment.TopEnd
            )
        Box {
            Text(
                text = title,
                fontSize = 48.sp,
                fontWeight = FontWeight.W400,
                fontFamily = Cherry_Bomb,
                color = AppTheme.colors.primary,
                style = TextStyle(
                    drawStyle = Stroke(
                        width = strokeInPx
                    )
                )
            )
            Text(
                text = title,
                fontSize = 48.sp,
                fontWeight = FontWeight.W400,
                fontFamily = Cherry_Bomb,
                color = AppTheme.colors.onPrimary,
                )

        }


    }

}