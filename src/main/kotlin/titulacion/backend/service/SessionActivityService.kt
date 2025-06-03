package titulacion.backend.service


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import titulacion.backend.model.Sesion
import titulacion.backend.model.SessionActivity
import titulacion.backend.model.SessionActivityId
import titulacion.backend.repository.ActivityRepository

import titulacion.backend.repository.SessionActivityRepository

@Service
class SessionActivityService {

    @Autowired
    lateinit var sessionActivityRepository: SessionActivityRepository

    @Autowired
    lateinit var activityRepository: ActivityRepository

    fun assignActivitiesToSession(sesion: Sesion) {
        val sessionDifficulty = sesion.difficulty ?: throw Exception("Sesión sin dificultad")

        // Obtener actividades de la misma dificultad
        val allByDifficulty = activityRepository.findAll()
            .filter { it.difficulty == sessionDifficulty }

        // Validar que todas tengan un tipo
        val withNullTypes = allByDifficulty.filter { it.type == null }
        if (withNullTypes.isNotEmpty()) {
            throw Exception("Hay actividades con dificultad $sessionDifficulty que no tienen tipo asignado")
        }

        val groupedByType = allByDifficulty.groupBy { it.type!! }

        val selectedActivities = groupedByType.values.mapNotNull { it.shuffled().firstOrNull() }

        val sessionActivities = selectedActivities.map { activity ->
            SessionActivity().apply {
                this.sesion = sesion
                this.activity = activity
            }
        }

        sessionActivityRepository.saveAll(sessionActivities)
    }

    fun assignActivitiesManually(sesion: Sesion, activityIds: List<Long>) {
        val sessionDifficulty = sesion.difficulty ?: throw Exception("Sesión sin dificultad")

        val activities = activityRepository.findAllById(activityIds)

        if (activities.isEmpty()) throw IllegalArgumentException("No se encontraron actividades válidas")

        // Validar que todas sean de la misma dificultad
        if (activities.any { it.difficulty != sessionDifficulty }) {
            throw IllegalArgumentException("Todas las actividades deben ser de dificultad correspondiente a la sesión")
        }

        // Validar que no haya más de una por tipo
        val grouped = activities.groupBy { it.type }

        for ((type, lista) in grouped) {
            if (lista.size > 1) {
                throw IllegalArgumentException("Solo se permite una actividad por tipo ($type)")
            }
        }

        val sessionActivities = activities.map { activity ->
            SessionActivity().apply {
                this.sesion = sesion
                this.activity = activity
            }
        }

        sessionActivityRepository.saveAll(sessionActivities)
    }

    fun findBySesionId(sesionId: Long): List<SessionActivity> {
        return sessionActivityRepository.findBySesionId(sesionId)
    }

    fun list(): List<SessionActivity> {
        return sessionActivityRepository.findAll()
    }

    fun getById(id: SessionActivityId): SessionActivity? {
        return sessionActivityRepository.findById(id).orElse(null)
    }

    fun create(sessionActivity: SessionActivity): SessionActivity {
        return sessionActivityRepository.save(sessionActivity)
    }

    fun delete(id: SessionActivityId) {
        if (sessionActivityRepository.existsById(id)) {
            sessionActivityRepository.deleteById(id)
        }
    }
}

