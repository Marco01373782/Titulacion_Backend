
package titulacion.backend.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import titulacion.backend.model.ActivityType
import titulacion.backend.repository.ActivityTypeRepository

@Service
class ActivityTypeService {

    @Autowired
    lateinit var repository: ActivityTypeRepository

    fun list(): List<ActivityType> = repository.findAll()

    fun getById(id: Long): ActivityType? = repository.findById(id).orElse(null)

    fun create(type: ActivityType): ActivityType = repository.save(type)

    fun delete(id: Long) {
        if (repository.existsById(id)) repository.deleteById(id)
    }
}
