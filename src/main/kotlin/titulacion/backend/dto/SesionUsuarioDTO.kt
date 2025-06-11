package titulacion.backend.dto

import titulacion.backend.enums.StatusSesion
import java.time.LocalDate
import java.time.LocalDateTime

data class SesionUsuarioDTO(
    val id: Long,
    val sesionId: Long,
    val userId: Long,
    val status: StatusSesion,
    val startedAt: LocalDateTime?,
    val endedAt: LocalDateTime?,
    val sessionDurationSeconds: Int?,
    val result: Double?,
    val mode: String?,
    val date: LocalDate?
)