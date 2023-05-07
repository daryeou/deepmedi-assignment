package com.daryeou.app.core.domain.usecase.deepmedi

import com.daryeou.app.core.domain.entities.deepmedi.DeepmediHealthResultEntity
import com.daryeou.app.core.domain.model.ApiResult
import com.daryeou.app.core.domain.repository.DeepmediRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHealthResult @Inject constructor(
    private val deepmediRepo: DeepmediRepo
) {
    operator fun invoke(): Flow<ApiResult<DeepmediHealthResultEntity>> {
        return deepmediRepo.getHealthResult()
    }
}