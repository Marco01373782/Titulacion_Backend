package titulacion.backend.service


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import titulacion.backend.model.Activity
import titulacion.backend.repository.ActivityRepository

@Service
class ActivityService {

    @Autowired
    lateinit var activityRepository: ActivityRepository

    fun list(): List<Activity> {
        return activityRepository.findAll()
    }

    fun getById(id: Long): Activity? {
        return activityRepository.findById(id).orElse(null)
    }

    fun create(activity: Activity): Activity {
        return activityRepository.save(activity)
    }

    fun delete(id: Long) {
        if (activityRepository.existsById(id)) {
            activityRepository.deleteById(id)
        }
    }
    fun save(activity: Activity): Activity {
        return activityRepository.save(activity)
    }

}
