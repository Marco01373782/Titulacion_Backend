package titulacion.backend.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class SessionActivityResultDTO(
    val sesionUsuarioId: Long,
    val activityId: Long,
    val result: BigDecimal?,
    val completedAt: LocalDateTime?,
    val durationSeconds: Int?
)
