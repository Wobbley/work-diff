package no.lozo.workdiff.dto

import kotlin.time.ExperimentalTime

@ExperimentalTime
data class DiffResponse(val loggedSeconds: Int,
                        val expectedSeconds: Int,
                        val diffSeconds: Int,
                        val diffHours: Double)