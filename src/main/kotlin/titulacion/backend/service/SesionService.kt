package titulacion.backend.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import titulacion.backend.model.Sesion
import titulacion.backend.repository.SesionRepository

@Service
class SesionService {

    @Autowired
    private lateinit var sessionActivityService: SessionActivityService

    @Autowired
    lateinit var sesionRepository: SesionRepository

    fun list(): List<Sesion> {
        return sesionRepository.findAll()
    }

    fun getById(id: Long): Sesion? {
        return sesionRepository.findById(id).orElse(null)
    }

    fun create(sesion: Sesion): Sesion {
        val savedSesion = sesionRepository.save(sesion)
        // Asignar actividades automáticamente
        sessionActivityService.assignActivitiesToSession(savedSesion)
        return savedSesion
    }
    fun update(id: Long, updatedSesion: Sesion): Sesion {
        val existing = getById(id) ?: throw NoSuchElementException("Sesión no encontrada")

        existing.title = updatedSesion.title
        existing.description = updatedSesion.description

        return sesionRepository.save(existing)
    }


    fun delete(id: Long) {
        if (sesionRepository.existsById(id)) {
            sesionRepository.deleteById(id)
        }
    }
}
