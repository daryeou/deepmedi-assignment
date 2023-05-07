package com.daryeou.app.core.data.mapper

import com.daryeou.app.core.domain.entities.deepmedi.DeepmediHealthResultEntity
import com.daryeou.app.core.domain.entities.deepmedi.DeepmediUploadImageEntity
import com.daryeou.app.core.model.deepmedi.HealthData
import com.daryeou.app.core.model.deepmedi.UserData
import com.daryeou.app.core.network.model.DeepmediResultResponse
import com.daryeou.app.core.network.model.DeepmediUploadImageResponse
import retrofit2.HttpException
import retrofit2.Response

object DeepmediMapper {
    fun DeepmediUploadImageResponse.toDomain(): DeepmediUploadImageEntity {
        if (code != 200) throw HttpException(Response.success(this))
        return DeepmediUploadImageEntity(
            code = code,
            message = message,
        )
    }

    fun DeepmediResultResponse.toDomain() = DeepmediHealthResultEntity(
        userData = UserData(
            name = this.name,
            driverPenaltyPoint = this.driverPenaltyPoint,
            profileImageUrl = this.profileImageUrl,
        ),
        healthData = HealthData(
            bpm = this.bpm,
            sys = this.sys,
            dia = this.dia,
            respiratoryRate = this.respiratoryRate,
            fatigue = this.fatigue,
            stress = this.stress,
            temp = this.temp,
            alcohol = this.alcohol,
            spo2 = this.spo2,
        )
    )
}