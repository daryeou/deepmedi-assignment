package com.daryeou.app.feature.deepmediresult

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.daryeou.app.core.designsystem.component.LoadingWheel
import com.daryeou.app.core.designsystem.theme.AppTheme
import com.daryeou.app.core.domain.entities.deepmedi.DeepmediHealthResultEntity
import com.daryeou.app.core.model.deepmedi.HealthData
import com.daryeou.app.core.model.deepmedi.UserData
import com.daryeou.app.feature.deepmediresult.DeepmediResultViewModel.HealthResultUiState

@Composable
internal fun DeepmediResultRoute(
    onNavigateToHome: () -> Unit,
    showMessage: (String) -> Unit,
    viewModel: DeepmediResultViewModel = hiltViewModel()
) {
    val uiState by viewModel.healthResultState.collectAsStateWithLifecycle()

    when (uiState) {
        HealthResultUiState.LOADING -> {
            LoadingWheel()
        }

        is HealthResultUiState.SUCCESS -> {
            DeepmediResultScreen(
                resultData = (uiState as HealthResultUiState.SUCCESS).result,
                onNavigateToHome = onNavigateToHome
            )
        }

        HealthResultUiState.ERROR -> {
            showMessage(stringResource(id = R.string.error_message))
        }
    }
}



@Preview
@Composable
internal fun DeepmediResultScreenPreview() {
    AppTheme() {
        DeepmediResultScreen(
            onNavigateToHome = {},
            resultData = DeepmediHealthResultEntity(
                userData = UserData(
                    name = "김다롱",
                    profileImageUrl = "",
                    driverPenaltyPoint = 10
                ),
                healthData = HealthData(
                    bpm = 100,
                    sys = 120,
                    dia = 80,
                    respiratoryRate = 20,
                    fatigue = 10,
                    stress = 10,
                    temp = 36.5,
                    alcohol = false,
                    spo2 = 100,
                )
            )
        )
    }
}