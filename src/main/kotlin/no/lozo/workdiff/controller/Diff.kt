package no.lozo.workdiff.controller

import no.lozo.workdiff.dto.DiffRequest
import no.lozo.workdiff.dto.DiffResponse
import no.lozo.workdiff.calculateWorkDays
import no.lozo.workdiff.clockify.ClockifyClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

@RestController
class Diff {

    @PostMapping("/diff")
    @ExperimentalTime
    fun difference(@RequestBody diffRequest: DiffRequest): DiffResponse {

        val (workspaceId, apiKey, start, end) = diffRequest;

        val loggedSeconds = ClockifyClient.getSummaryReport(workspaceId, apiKey, start, end).toDuration(DurationUnit.SECONDS)
        val workingDays = calculateWorkDays(diffRequest.startDate, diffRequest.endDate)
        val workingSeconds = workingDays.times(8).times(60).times(60).toDuration(DurationUnit.SECONDS)
        val diffSeconds = loggedSeconds.minus(workingSeconds)

        return DiffResponse(
                loggedSeconds.inHours,
                workingSeconds.inHours,
                diffSeconds.inHours)
    }
}
