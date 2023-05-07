package com.daryeou.app.core.domain.usecase.deepmedi

import com.daryeou.app.core.domain.entities.deepmedi.DeepmediUploadImageEntity
import com.daryeou.app.core.domain.model.ApiResult
import com.daryeou.app.core.domain.repository.DeepmediRepo
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

class UploadFaceImage @Inject constructor(
    private val deepmediRepo: DeepmediRepo
) {
    operator fun invoke(imageFile: File): Flow<ApiResult<DeepmediUploadImageEntity>> {

        return deepmediRepo.uploadFaceImage(imageFile)
    }
}