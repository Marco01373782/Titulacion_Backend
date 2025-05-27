package titulacion.backend.controller

import titulacion.backend.model.Activity
import titulacion.backend.service.ActivityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/activities")
class ActivityController {

    @Autowired
    lateinit var activityService: ActivityService

    @GetMapping
    fun list(): List<Activity> {
        return activityService.list()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Activity? {
        return activityService.getById(id)
    }

    @PostMapping
    fun create(@RequestBody activity: Activity): Activity {
        return activityService.create(activity)
    }
    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody updateData: Map<String, Any>
    ): ResponseEntity<Activity> {
        val existingActivity = activityService.getById(id) ?: return ResponseEntity.notFound().build()

        // Actualizar solo los campos permitidos si están presentes en el cuerpo
        updateData["title"]?.let { existingActivity.title = it as String }
        updateData["description"]?.let { existingActivity.description = it as String }
        updateData["resourceUrl"]?.let { existingActivity.resourceUrl = it as String }

        val updated = activityService.save(existingActivity)
        return ResponseEntity.ok(updated)   
    }


    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        activityService.delete(id)
    }
}
