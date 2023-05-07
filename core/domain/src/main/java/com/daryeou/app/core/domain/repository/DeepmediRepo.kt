package com.daryeou.app.core.domain.repository

import com.daryeou.app.core.domain.entities.deepmedi.DeepmediHealthResultEntity
import com.daryeou.app.core.domain.entities.deepmedi.DeepmediUploadImageEntity
import com.daryeou.app.core.domain.model.ApiResult
import kotlinx.coroutines.flow.Flow
import java.io.File

interface DeepmediRepo {
    fun uploadFaceImage(imageFile: File): Flow<ApiResult<DeepmediUploadImageEntity>>

    fun getHealthResult(): Flow<ApiResult<DeepmediHealthResultEntity>>
}