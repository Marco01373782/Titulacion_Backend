package titulacion.backend.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import titulacion.backend.dto.SesionUsuarioDTO
import titulacion.backend.enums.StatusSesion
import titulacion.backend.model.Sesion
import titulacion.backend.model.SesionUsuario
import titulacion.backend.repository.SesionRepository
import titulacion.backend.repository.SesionUsuarioRepository
import titulacion.backend.repository.UserRepository

@Service
class SesionUsuarioService {

    @Autowired
    lateinit var sesionUsuarioRepository: SesionUsuarioRepository

    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var sesionRepository: SesionRepository


    // Método para asignar una sesión a todos los usuarios
    fun assignSessionToAllUsers(sesion: Sesion) {
        val usuarios = userRepository.findByRoles("user")


        usuarios.forEach { user ->
            // Evitar duplicados (por si acaso)
            val existing = sesionUsuarioRepository.findBySesionIdAndUserId(sesion.id!!, user.id!!)
            if (existing == null) {
                val sesionUsuario = SesionUsuario().apply {
                    this.sesion = sesion
                    this.user = user
                    this.status = StatusSesion.ACTIVA
                    // Puedes inicializar otros campos si quieres, como mode, date, etc.
                }
                sesionUsuarioRepository.save(sesionUsuario)
            }
        }
    }


    fun list(): List<SesionUsuario> = sesionUsuarioRepository.findAll()

    fun getById(id: Long): SesionUsuario? = sesionUsuarioRepository.findById(id).orElse(null)

    fun getBySesionAndUser(sesionId: Long, userId: Long): SesionUsuario? =
        sesionUsuarioRepository.findBySesionIdAndUserId(sesionId, userId)

    fun create(sesionUsuario: SesionUsuario): SesionUsuario =
        sesionUsuarioRepository.save(sesionUsuario)

    fun update(id: Long, updated: SesionUsuario): SesionUsuario {
        val existing = getById(id) ?: throw NoSuchElementException("SesiónUsuario no encontrada")

        updated.status?.let { existing.status = it }
        existing.startedAt = updated.startedAt
        existing.endedAt = updated.endedAt
        existing.sessionDurationSeconds = updated.sessionDurationSeconds
        existing.result = updated.result
        existing.mode = updated.mode
        existing.date = updated.date

        return sesionUsuarioRepository.save(existing)
    }



    fun delete(id: Long) {
        if (sesionUsuarioRepository.existsById(id)) {
            sesionUsuarioRepository.deleteById(id)
        }
    }

    fun findAllByUser(userId: Long): List<SesionUsuario> =
        sesionUsuarioRepository.findAllByUserId(userId)


    fun assignAllExistingSessionsToUser(userId: Long) {
        val user = userRepository.findById(userId).orElseThrow { RuntimeException("Usuario no encontrado") }

        val sesiones = sesionRepository.findAll()  // ¡Usamos el SesionRepository!

        sesiones.forEach { sesion ->
            val yaExiste = sesionUsuarioRepository.findBySesionIdAndUserId(sesion.id!!, user.id!!)
            if (yaExiste == null) {
                val sesionUsuario = SesionUsuario().apply {
                    this.sesion = sesion
                    this.user = user
                    this.status = StatusSesion.ACTIVA
                }
                sesionUsuarioRepository.save(sesionUsuario)
            }
        }
    }


}
