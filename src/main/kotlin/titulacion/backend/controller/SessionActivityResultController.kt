package titulacion.backend.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import titulacion.backend.dto.SessionActivityResultDTO
import titulacion.backend.model.SessionActivityResult
import titulacion.backend.repository.ActivityRepository
import titulacion.backend.repository.SesionRepository
import titulacion.backend.repository.SesionUsuarioRepository
import titulacion.backend.repository.UserRepository
import titulacion.backend.service.SessionActivityResultService
import java.math.BigDecimal
import java.time.LocalDateTime


@RestController
@RequestMapping("/session-activity-results")
class SessionActivityResultController(
    private val service: SessionActivityResultService,
    private val sesionUsuarioRepository: SesionUsuarioRepository,
    private val activityRepository: ActivityRepository
) {

    @PostMapping
    fun saveResult(@RequestBody dto: SessionActivityResultDTO): ResponseEntity<SessionActivityResult> {
        val sesionUsuario = sesionUsuarioRepository.findById(dto.sesionUsuarioId)
            .orElseThrow { RuntimeException("No se encontró la sesión del usuario con ID ${dto.sesionUsuarioId}") }

        val sesion = sesionUsuario.sesion!!
        val user = sesionUsuario.user!!
        val activity = activityRepository.findById(dto.activityId).orElseThrow()

        val result = SessionActivityResult(
            sesion = sesion,
            activity = activity,
            user = user,
            result = dto.result,
            completedAt = dto.completedAt ?: LocalDateTime.now(),
            durationSeconds = dto.durationSeconds
        )

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
    @GetMapping("/average")
    fun getAverageForSessionAndUser(
        @RequestParam sessionId: Long,
        @RequestParam userId: Long
    ): ResponseEntity<BigDecimal> {
        val average = service.calculateNormalizedAverage(sessionId, userId)
        return ResponseEntity.ok(average)
    }

}
