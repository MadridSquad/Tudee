import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.presentation.components.TextLogo
import com.washingtondcsquad.tudee.presentation.design.AppTheme

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    title: String,
    isDarkTheme: Boolean,
){
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colors.surface),
        contentAlignment = Alignment.Center

    ){
       Image(
           painter = if (isDarkTheme) painterResource(R.drawable.background_graphics_dark) else painterResource(
               R.drawable.background_graphics
           ),
           contentDescription = stringResource(R.string.background_graphics),
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.overlay),
            alignment = Alignment.TopEnd
            )
        TextLogo(title, 48)


    }

}


