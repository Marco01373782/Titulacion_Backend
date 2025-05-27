
package titulacion.backend.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import titulacion.backend.model.ActivityType
import titulacion.backend.service.ActivityTypeService

@RestController
@RequestMapping("/activity-types")
class ActivityTypeController {

    @Autowired
    lateinit var service: ActivityTypeService

    @GetMapping
    fun list(): List<ActivityType> = service.list()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ActivityType? = service.getById(id)

    @PostMapping
    fun create(@RequestBody type: ActivityType): ActivityType = service.create(type)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = service.delete(id)
}
