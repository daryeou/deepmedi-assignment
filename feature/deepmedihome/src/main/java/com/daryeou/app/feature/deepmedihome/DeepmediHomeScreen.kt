package com.daryeou.app.feature.deepmedihome

import android.net.Uri
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daryeou.app.core.designsystem.theme.AppTheme
import com.daryeou.app.core.designsystem.theme.Colors
import com.daryeou.app.feature.deepmedihome.component.CameraPreview
import com.daryeou.app.feature.deepmedihome.utils.takePhoto
import kotlinx.coroutines.delay

const val navigateToResultDelay = 1000L

@Composable
internal fun DeepmediHomeScreen(
    uiState: DeepmediHomeState,
    onNavigateToResult: () -> Unit,
    onImageCaptured: (Uri) -> Unit,
    showMessage: (String) -> Unit,
) {
    val context = LocalContext.current
    val lensFacing by remember { mutableStateOf(CameraSelector.LENS_FACING_FRONT) }
    val imageCapture: ImageCapture = remember {
        ImageCapture.Builder().build()
    }

    var homeDescription by remember { mutableStateOf(buildAnnotatedString {}) }
    var enableCapture by remember { mutableStateOf(true) }
    var showCheckSymbol by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = uiState) {
        showCheckSymbol = false

        when (uiState) {
            is DeepmediHomeState.Idle -> {
                homeDescription = buildAnnotatedString {
                    append(context.getString(R.string.deepmedi_home_description_start))
                    withStyle(style = SpanStyle(color = Colors.red_primary)) {
                        append(context.getString(R.string.deepmedi_home_description_highlight))
                    }
                    append(context.getString(R.string.deepmedi_home_description_end))
                }
                enableCapture = true
            }

            is DeepmediHomeState.Loading -> {
                homeDescription = buildAnnotatedString {
                    append(context.getString(R.string.deepmedi_home_description_loading))
                }
                enableCapture = false
            }

            is DeepmediHomeState.Success -> {
                homeDescription = buildAnnotatedString {
                    append(context.getString(R.string.deepmedi_home_description_result))
                    withStyle(SpanStyle(color = Colors.red_primary)) {
                        append(context.getString(R.string.deepmedi_home_description_result_success))
                    }
                }
                showCheckSymbol = true
                delay(navigateToResultDelay)
                onNavigateToResult()
            }

            is DeepmediHomeState.Error -> {
                homeDescription = buildAnnotatedString {
                    append(context.getString(R.string.deepmedi_home_description_result))
                    withStyle(SpanStyle(color = Colors.red_primary)) {
                        append(context.getString(R.string.deepmedi_home_description_result_failed))
                    }
                }
                enableCapture = true
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .padding(start = 38.dp)
                .padding(vertical = 16.dp)
                .wrapContentWidth(align = BiasAlignment.Horizontal(-0.8f)),
            text = stringResource(id = R.string.deepmedi_home_title),
            fontSize = 16.sp,
        )
        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Colors.divider
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.weight(0.1f))
            Text(
                modifier = Modifier
                    .wrapContentHeight(),
                text = homeDescription,
                fontSize = 16.sp,
                fontWeight = FontWeight(700),
                lineHeight = 26.sp,
                textAlign = TextAlign.Center,
                minLines = 2,
                maxLines = 2,
            )
            Spacer(modifier = Modifier.weight(0.1f))
            DeepmediHomePreviewSection(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(0.8f),
                lensFacing = lensFacing,
                imageCapture = imageCapture,
                enableCapture = enableCapture,
                onImageCaptured = onImageCaptured,
                onCaptureFailed = {
                    showMessage(context.getString(R.string.capture_failed_message))
                },
                showCheckSymbol = showCheckSymbol,
            )
            Spacer(modifier = Modifier.weight(0.2f))
            Image(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .weight(0.2f)
                    .wrapContentHeight(),
                painter = painterResource(R.drawable.img_home_bottom),
                contentDescription = "Home Bottom Image",
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun DeepmediHomePreviewSection(
    modifier: Modifier = Modifier,
    enableCapture: Boolean,
    imageCapture: ImageCapture,
    lensFacing: Int,
    showCheckSymbol: Boolean,
    onImageCaptured: (Uri) -> Unit,
    onCaptureFailed: (ImageCaptureException) -> Unit,
) {
    val context = LocalContext.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
                .border(
                    width = 3.dp,
                    color = Colors.red_primary,
                ),
        ) {
            CameraPreview(
                modifier = Modifier
                    .fillMaxSize(),
                cameraBind = enableCapture,
                imageCapture = imageCapture,
                lensFacing = lensFacing,
            )
            Spacer(modifier = Modifier.height(24.dp))
            if (showCheckSymbol) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0x2600FF80))
                        .fillMaxWidth(0.3f)
                        .wrapContentHeight()
                        .align(Alignment.Center),
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = null,
                )
            }
        }
        CaptureButton(
            modifier = Modifier
                .width(120.dp)
                .wrapContentHeight(),
            onClick = {
                takePhoto(
                    context = context,
                    imageCapture = imageCapture,
                    lensFacing = lensFacing,
                    onImageCaptured = onImageCaptured,
                    onCaptureFailed = onCaptureFailed
                )
            },
            isEnable = enableCapture,
        )
    }
}

@Composable
private fun CaptureButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isEnable: Boolean,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = isEnable,
    ) {
        Text(
            text = stringResource(id = R.string.deepmedi_home_capture_button)
        )
    }
}

@Composable
@Preview
internal fun DeepmediHomeScreenPreview() {
    AppTheme() {
        DeepmediHomeScreen(
            uiState = DeepmediHomeState.Idle,
            onNavigateToResult = {},
            onImageCaptured = {},
            showMessage = {},
        )
    }
}