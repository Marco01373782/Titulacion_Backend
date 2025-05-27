package titulacion.backend.service


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import titulacion.backend.model.Activity
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
        val difficultyId = sesion.difficulty?.id ?: throw Exception("Sesión sin dificultad")

        // Validación adicional: asegurarse de que no hay actividades sin tipo
        val allByDifficulty = activityRepository.findAll()
            .filter { it.difficulty?.id == difficultyId }

        val withNullTypes = allByDifficulty.filter { it.type?.id == null }
        if (withNullTypes.isNotEmpty()) {
            throw Exception("Hay actividades con dificultad $difficultyId que no tienen tipo asignado")
        }

        // Filtrar solo las actividades con tipo válido
        val activitiesByDifficulty = allByDifficulty.filter { it.type?.id != null }

        val groupedByType = activitiesByDifficulty.groupBy { it.type!!.id }

        val selectedActivities = groupedByType.values.mapNotNull { it.shuffled().firstOrNull() }

        val sessionActivities = selectedActivities.map { activity ->
            SessionActivity().apply {
                this.sesion = sesion
                this.activity = activity
            }
        }

        sessionActivityRepository.saveAll(sessionActivities)
    }

    // ✅ Manual: también una sola por tipo
    fun assignActivitiesManually(sesion: Sesion, activityIds: List<Long>) {
        val difficultyId = sesion.difficulty?.id ?: throw Exception("Sesión sin dificultad")

        val activities = activityRepository.findAllById(activityIds)

        if (activities.isEmpty()) throw IllegalArgumentException("No se encontraron actividades válidas")

        // Validar que todas sean de la misma dificultad que la sesión
        if (activities.any { it.difficulty?.id != difficultyId }) {
            throw IllegalArgumentException("Todas las actividades deben ser de dificultad correspondiente a la sesión")
        }

        val grouped = activities.groupBy { it.type?.id }

        for ((typeId, lista) in grouped) {
            if (lista.size > 1) {
                throw IllegalArgumentException("Solo se permite una actividad por tipo (tipo ID: $typeId)")
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

    // Cambiar Long por SessionActivityId
    fun getById(id: SessionActivityId): SessionActivity? {
        return sessionActivityRepository.findById(id).orElse(null)
    }

    // Cambiar Long por SessionActivityId
    fun create(sessionActivity: SessionActivity): SessionActivity {
        return sessionActivityRepository.save(sessionActivity)
    }

    // Cambiar Long por SessionActivityId
    fun delete(id: SessionActivityId) {
        if (sessionActivityRepository.existsById(id)) {
            sessionActivityRepository.deleteById(id)
        }
    }



}

