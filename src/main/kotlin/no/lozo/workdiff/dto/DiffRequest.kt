package no.lozo.workdiff.dto

import java.time.LocalDate

data class DiffRequest(val workspaceId: String,
                       val apiKey: String,
                       val start: LocalDate = LocalDate.now().minusMonths(1),
                       val end: LocalDate = LocalDate.now())