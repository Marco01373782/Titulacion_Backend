package titulacion.backend.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import titulacion.backend.dto.SesionUsuarioDTO
import titulacion.backend.dto.toDTO
import titulacion.backend.enums.StatusSesion
import titulacion.backend.model.SesionUsuario
import titulacion.backend.service.SesionUsuarioService

@RestController
@RequestMapping("/sesion-usuario")
class SesionUsuarioController {

    @Autowired
    lateinit var sesionUsuarioService: SesionUsuarioService

    @GetMapping
    fun list(): List<SesionUsuarioDTO> =
        sesionUsuarioService.list().map { it.toDTO() }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): SesionUsuarioDTO {
        val sesionUsuario = sesionUsuarioService.getById(id)
            ?: throw RuntimeException("No existe la sesión")
        return sesionUsuario.toDTO()
    }


    @GetMapping("/sesion/{sesionId}/user/{userId}")
    fun getBySesionAndUser(@PathVariable sesionId: Long, @PathVariable userId: Long): SesionUsuario? =
        sesionUsuarioService.getBySesionAndUser(sesionId, userId)

    @PostMapping
    fun create(@RequestBody sesionUsuario: SesionUsuario): SesionUsuario =
        sesionUsuarioService.create(sesionUsuario)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        sesionUsuarioService.delete(id)
    }

    @GetMapping("/user/{userId}")
    fun findAllByUser(@PathVariable userId: Long): List<SesionUsuario> =
        sesionUsuarioService.findAllByUser(userId)

    @PatchMapping("/{id}/status")
    fun updateStatus(@PathVariable id: Long, @RequestParam status: String): SesionUsuario {
        val sesionUsuario = sesionUsuarioService.getById(id)
            ?: throw RuntimeException("SesiónUsuario no encontrada con ID: $id")

        try {
            val statusEnum = StatusSesion.valueOf(status.uppercase())
            sesionUsuario.status = statusEnum
            return sesionUsuarioService.update(id, sesionUsuario)
        } catch (e: IllegalArgumentException) {
            throw RuntimeException("Estado inválido: $status. Debe ser uno de: ${StatusSesion.values().joinToString()}")
        }
    }
    @PutMapping("/{id}")
    fun updateSesionUsuario(@PathVariable id: Long, @RequestBody updated: SesionUsuario): SesionUsuario {
        val existing = sesionUsuarioService.getById(id)
            ?: throw RuntimeException("No existe SesionUsuario con id $id")
        existing.status = updated.status
        existing.startedAt = updated.startedAt
        existing.endedAt = updated.endedAt
        existing.sessionDurationSeconds = updated.sessionDurationSeconds
        existing.result = updated.result
        existing.mode = updated.mode
        existing.date = updated.date

        return sesionUsuarioService.update(id, existing)
    }



}
