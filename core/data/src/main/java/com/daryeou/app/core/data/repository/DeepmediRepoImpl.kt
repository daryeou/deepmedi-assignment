package com.daryeou.app.core.data.repository

import com.daryeou.app.core.data.mapper.DeepmediMapper.toDomain
import com.daryeou.app.core.data.util.safeFlow
import com.daryeou.app.core.domain.entities.deepmedi.DeepmediHealthResultEntity
import com.daryeou.app.core.domain.entities.deepmedi.DeepmediUploadImageEntity
import com.daryeou.app.core.domain.model.ApiResult
import com.daryeou.app.core.domain.repository.DeepmediRepo
import com.daryeou.app.core.network.source.DeepmediDataSource
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

class DeepmediRepoImpl @Inject constructor(
    private val deepmediDataSource: DeepmediDataSource,
) : DeepmediRepo {

    override fun uploadFaceImage(imageFile: File): Flow<ApiResult<DeepmediUploadImageEntity>> {
        return safeFlow {
            deepmediDataSource.uploadImageToDeepmedi(imageFile).toDomain()
        }
    }

    override fun getHealthResult(): Flow<ApiResult<DeepmediHealthResultEntity>> {
        return safeFlow {
            deepmediDataSource.getHealthResult().toDomain()
        }
    }
}