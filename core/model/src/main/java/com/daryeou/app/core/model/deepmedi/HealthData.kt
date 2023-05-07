package com.daryeou.app.core.model.deepmedi

data class HealthData(
    val bpm: Int,
    val sys: Int,
    val dia: Int,
    val respiratoryRate: Int,
    val fatigue: Int,
    val stress: Int,
    val temp: Double,
    val alcohol: Boolean,
    val spo2: Int
)
