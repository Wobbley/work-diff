package no.lozo.workdiff.dto

import java.time.LocalDate

data class DiffRequest(val workspaceId: String,
                       val apiKey: String,
                       val startDate: LocalDate,
                       val endDate: LocalDate,
                       val hoursInWorkday: Double = 8.0)