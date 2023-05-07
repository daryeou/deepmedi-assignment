package com.daryeou.app.core.domain.entities.deepmedi

import com.daryeou.app.core.model.deepmedi.HealthData
import com.daryeou.app.core.model.deepmedi.UserData

data class DeepmediHealthResultEntity(
    val userData: UserData,
    val healthData: HealthData,
)