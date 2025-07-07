package titulacion.backend.dto

import titulacion.backend.model.SesionUsuario

    fun SesionUsuario.toDTO(): SesionUsuarioDTO = SesionUsuarioDTO(
        id = requireNotNull(this.id) { "ID no puede ser null" },
        sesionId = requireNotNull(this.sesion?.id) { "Sesion o su ID no pueden ser null" },
        userId = requireNotNull(this.user?.id) { "User o su ID no pueden ser null" },
        status = this.status,
        startedAt = this.startedAt,
        endedAt = this.endedAt,
        sessionDurationSeconds = this.sessionDurationSeconds,
        result = this.result?.toDouble(),
        mode = this.mode,
        date = this.date
    )

