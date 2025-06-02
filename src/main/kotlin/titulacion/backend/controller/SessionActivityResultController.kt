package titulacion.backend.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import titulacion.backend.model.SessionActivityResult
import titulacion.backend.service.SessionActivityResultService

@RestController
@RequestMapping("/session-activity-results")
class SessionActivityResultController(
    private val service: SessionActivityResultService
) {

    @PostMapping
    fun saveResult(@RequestBody result: SessionActivityResult): ResponseEntity<SessionActivityResult> {
        val saved = service.save(result)
        return ResponseEntity.ok(saved)
    }   

    @GetMapping("/by-session/{sessionId}")
    fun getBySession(@PathVariable sessionId: Long): ResponseEntity<List<SessionActivityResult>> {
        return ResponseEntity.ok(service.getBySessionId(sessionId))
    }

    @GetMapping("/by-user/{userId}")
    fun getByUser(@PathVariable userId: Long): ResponseEntity<List<SessionActivityResult>> {
        return ResponseEntity.ok(service.getByUserId(userId))
    }

    @GetMapping("/by-session-user")
    fun getBySessionAndUser(
        @RequestParam sessionId: Long,
        @RequestParam userId: Long
    ): ResponseEntity<List<SessionActivityResult>> {
        return ResponseEntity.ok(service.getBySessionAndUser(sessionId, userId))
    }
}
