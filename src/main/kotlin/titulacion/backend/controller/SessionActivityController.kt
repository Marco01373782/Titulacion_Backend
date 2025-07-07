package titulacion.backend.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import titulacion.backend.dto.ManualAssignDTO
import titulacion.backend.model.SessionActivity
import titulacion.backend.model.SessionActivityId
import titulacion.backend.service.SessionActivityService
import titulacion.backend.service.SesionService

@RestController
@RequestMapping("/session-activities")
class SessionActivityController {

    @Autowired
    lateinit var sessionActivityService: SessionActivityService

    @Autowired
    lateinit var sesionService: SesionService

    @GetMapping
    fun list(): List<SessionActivity> {
        return sessionActivityService.list()
    }

    @GetMapping("/{sesionId}/{activityId}")
    fun getById(@PathVariable sesionId: Long, @PathVariable activityId: Long): SessionActivity? {
        val id = SessionActivityId(sesionId, activityId)
        return sessionActivityService.getById(id)
    }

    @PostMapping
    fun create(@RequestBody sessionActivity: SessionActivity): SessionActivity {
        return sessionActivityService.create(sessionActivity)
    }

    @DeleteMapping("/{sesionId}/{activityId}")
    fun delete(@PathVariable sesionId: Long, @PathVariable activityId: Long) {
        val id = SessionActivityId(sesionId, activityId)
        sessionActivityService.delete(id)
    }

    // NUEVO: Asignar actividades automáticamente a una sesión
    @PostMapping("/assign/{sesionId}")
    fun assignActivities(@PathVariable sesionId: Long): String {
        val sesion = sesionService.getById(sesionId) ?: return "Sesión no encontrada"
        sessionActivityService.assignActivitiesToSession(sesion)
        return "Actividades asignadas correctamente a la sesión $sesionId"
    }

    // NUEVO: Listar actividades asignadas a una sesión
    @GetMapping("/by-session/{sesionId}")
    fun getBySession(@PathVariable sesionId: Long): List<SessionActivity> {
        return sessionActivityService.findBySesionId(sesionId)
    }
    @PostMapping("/assign-manual/{sesionId}")
    fun assignActivitiesManually(
        @PathVariable sesionId: Long,
        @RequestBody request: ManualAssignDTO
    ): String {
        val sesion = sesionService.getById(sesionId) ?: return "Sesión no encontrada"
        sessionActivityService.assignActivitiesManually(sesion, request.activityIds)
        return "Actividades asignadas manualmente a la sesión $sesionId"
    }

}
